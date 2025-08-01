package kr.ecommerce.be.server.interfaces.api.point;

import kr.ecommerce.be.server.domain.point.dto.request.UserPointServiceRequest;
import kr.ecommerce.be.server.domain.point.dto.response.UserPointServiceResponse;
import kr.ecommerce.be.server.domain.point.model.UserPoint;
import kr.ecommerce.be.server.domain.point.repository.PointRepository;
import kr.ecommerce.be.server.domain.point.service.PointService;
import kr.ecommerce.be.server.testhelper.RepositoryCleaner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;


@ActiveProfiles("local")
@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PointConcurrencyTest {

    @Container
    static final MySQLContainer<?> mysqlContainer = new MySQLContainer<>("mysql:8.0")
            .withDatabaseName("test")
            .withUsername("test")
            .withPassword("test");

    @Container
    static final GenericContainer<?> redisContainer = new GenericContainer<>("redis:7.0")
            .withExposedPorts(6379);

    @DynamicPropertySource
    static void overrideProperties(DynamicPropertyRegistry registry) {
        registry.add("test.kafka.topic", () -> "test-consumer-topic");
        registry.add("test.kafka.group", () -> "test-consumer-group");
        if (!mysqlContainer.isRunning()) {
            mysqlContainer.start();
        }
        registry.add("spring.datasource.url", mysqlContainer::getJdbcUrl);
        registry.add("spring.datasource.username", mysqlContainer::getUsername);
        registry.add("spring.datasource.password", mysqlContainer::getPassword);
        registry.add("spring.datasource.driver-class-name", mysqlContainer::getDriverClassName);
        registry.add("spring.jpa.database-platform", () -> "org.hibernate.dialect.MySQL8Dialect");
        registry.add("spring.sql.init.mode", () -> "always");
        registry.add("spring.sql.init.schema-locations", () -> "classpath:schema.sql");

        redisContainer.start();
        registry.add("spring.data.redis.host", redisContainer::getHost);
        registry.add("spring.data.redis.port", () -> redisContainer.getMappedPort(6379));
    }

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PointService pointService;

    @Autowired
    private PointRepository pointRepository;

    @Autowired
    private RepositoryCleaner repositoryCleaner;

    @BeforeEach
    void cleanup() {
        repositoryCleaner.cleanUpAll();
    }

    @Test
    @DisplayName("동시성 테스트: 여러 스레드가 동일한 유저에게 포인트를 동시에 충전")
    void concurrentChargeUserPoint_WithCountDownLatch() throws InterruptedException {
        long randomUserId = ThreadLocalRandom.current().nextInt(1, 100_000);
        int threadCount = 10;
        long chargeAmount = 1000;

        pointRepository.savePoint(UserPoint.of(randomUserId, 0L));
        CountDownLatch latch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            new Thread(() -> {
                try {
                    mockMvc.perform(put("/v1/point")
                                    .header("userId", randomUserId)
                                    .contentType("application/json")
                                    .content("""
                                    {
                                        "point": %d
                                    }
                                """.formatted(chargeAmount)))
                            .andReturn();
                } catch (Exception e) {
                    System.err.println("충전 중 예외 발생: " + e.getMessage());
                } finally {
                    latch.countDown();
                }
            }).start();
        }
        latch.await();

        // 결과 검증
        UserPointServiceResponse result = pointService.getUserPoint(new UserPointServiceRequest(randomUserId));
        assertEquals(threadCount * chargeAmount, result.point());
    }

    @Test
    @DisplayName("동시성 테스트: 여러 유저가 각각 동시에 포인트 동시에 충전")
    void concurrentChargeUserPointMultipleUsers() throws InterruptedException {
        int USER_COUNT = 3;
        int THREAD_COUNT_PER_USER = 10;
        long randomUserId = ThreadLocalRandom.current().nextInt(1, 100_000);
        long chargeAmount = 1000L;

        CountDownLatch latch = new CountDownLatch(USER_COUNT * THREAD_COUNT_PER_USER);
        for (int i = 0; i < USER_COUNT; i++) {
            long userId = randomUserId + i;
            pointRepository.savePoint(UserPoint.of(userId, 0L));
            for (int j = 0; j < THREAD_COUNT_PER_USER; j++) {
                new Thread(() -> {
                    try {
                        mockMvc.perform(put("/v1/point")
                                        .header("userId", userId)
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content("""
                                        {
                                            "point": %d
                                        }
                                    """.formatted(chargeAmount)))
                                .andReturn();
                    } catch (Exception e) {
                        System.err.println("유저 " + userId + " 충전 중 예외 발생: " + e.getMessage());
                    } finally {
                        latch.countDown();
                    }
                }).start();
            }
        }
        latch.await();

        for (int i = 0; i < USER_COUNT; i++) {
            long userId = randomUserId + i;
            UserPointServiceResponse result = pointService.getUserPoint(new UserPointServiceRequest(userId));
            assertEquals(THREAD_COUNT_PER_USER * chargeAmount, result.point(), "유저 " + userId + " 포인트 불일치");
        }
    }
}

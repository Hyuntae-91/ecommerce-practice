package kr.ecommerce.be.server.config;

import kr.ecommerce.be.server.application.publisher.MessagePublisher;
import kr.ecommerce.be.server.infrastructure.messaging.KafkaProducer;
import kr.ecommerce.be.server.interfaces.event.mockapi.payload.MockDataPlatformPayload;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration
public class MessagingConfig {

    @Bean
    public MessagePublisher<MockDataPlatformPayload> paymentCompletedEventPublisher(
            KafkaTemplate<String, MockDataPlatformPayload> kafkaTemplate
    ) {
        return new KafkaProducer<>(kafkaTemplate);
    }
}
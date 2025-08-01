package kr.ecommerce.be.server.interfaces.event.coupon;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.ecommerce.be.server.common.constants.Groups;
import kr.ecommerce.be.server.common.constants.Topics;
import kr.ecommerce.be.server.domain.coupon.dto.request.IssueNewCouponServiceRequest;
import kr.ecommerce.be.server.domain.coupon.service.CouponService;
import kr.ecommerce.be.server.interfaces.event.coupon.payload.CouponIssuePayload;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class CouponIssueProcessor {

    private final CouponService couponService;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = Topics.COUPON_ISSUE_TOPIC, groupId = Groups.COUPON_ISSUE_GROUP)
    public void consume(@Payload CouponIssuePayload event) {
        log.info("== [Kafka] Consumed message: userId={}, couponId={} ==", event.userId(), event.couponId());
        couponService.issueNewCouponToDb(new IssueNewCouponServiceRequest(event.userId(), event.couponId()));
    }
}

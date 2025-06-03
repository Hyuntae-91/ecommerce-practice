package kr.ecommerce.be.server.interfaces.event.coupon;

import kr.ecommerce.be.server.application.publisher.MessagePublisher;
import kr.ecommerce.be.server.common.constants.Groups;
import kr.ecommerce.be.server.common.constants.Topics;
import kr.ecommerce.be.server.domain.coupon.dto.response.ApplyCouponDiscountServiceResponse;
import kr.ecommerce.be.server.domain.coupon.mapper.CouponMapper;
import kr.ecommerce.be.server.domain.coupon.service.CouponService;
import kr.ecommerce.be.server.interfaces.event.coupon.payload.CouponUseRollbackPayload;
import kr.ecommerce.be.server.interfaces.event.coupon.payload.CouponApplyCompletedPayload;
import kr.ecommerce.be.server.interfaces.event.product.payload.ProductTotalPriceCompletedPayload;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class ProductTotalPriceCompletedConsumer {

    private final CouponService couponService;
    private final CouponMapper couponMapper;
    private final MessagePublisher<CouponApplyCompletedPayload> couponApplyCompletedPayloadPublisher;
    private final MessagePublisher<CouponUseRollbackPayload> couponUseRollbackPayloadMessagePublisher;

    @KafkaListener(topics = Topics.PRODUCT_TOTAL_PRICE_TOPIC, groupId = Groups.PRODUCT_TOTAL_PRICE_GROUP)
    public void consume(@Payload ProductTotalPriceCompletedPayload event) {
        try {
            ApplyCouponDiscountServiceResponse response = couponService.applyCouponDiscount(
                    couponMapper.toApplyCouponDiscountServiceRequest(event)
            );

            couponApplyCompletedPayloadPublisher.publish(
                    Topics.COUPON_APPLY_COMPLETE_TOPIC,
                    null,
                    couponMapper.toCouponApplyCompletedPayload(event, response)
            );
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            couponUseRollbackPayloadMessagePublisher.publish(
                    Topics.PRODUCT_TOTAL_PRICE_FAIL_ROLLBACK,
                    null,
                    couponMapper.toProductTotalPriceFailRollbackPayload(event)
            );
        }
    }
}

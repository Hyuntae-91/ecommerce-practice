package kr.ecommerce.be.server.interfaces.event.mockapi.payload;

import kr.ecommerce.be.server.interfaces.event.payment.payload.PaymentCompletedPayload;

import java.util.List;

public record MockDataPlatformPayload(Long orderId, Long paymentId, Long totalPrice, List<Long> productIds) {
    public static MockDataPlatformPayload from(PaymentCompletedPayload event) {
        return new MockDataPlatformPayload(
                event.orderId(),
                event.paymentId(),
                event.totalPrice(),
                event.productIds()
        );
    }
}
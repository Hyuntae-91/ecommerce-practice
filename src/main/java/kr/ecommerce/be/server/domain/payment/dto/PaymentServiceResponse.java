package kr.ecommerce.be.server.domain.payment.dto;

public record PaymentServiceResponse (
        Long paymentId,
        Long orderId,
        int status,         // 1 = 결제 완료
        Long totalPrice,
        String createdAt
) {}

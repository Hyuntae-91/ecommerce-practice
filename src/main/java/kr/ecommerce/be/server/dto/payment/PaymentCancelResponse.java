package kr.ecommerce.be.server.dto.payment;

public record PaymentCancelResponse(
        Long orderId,
        int status
) {}

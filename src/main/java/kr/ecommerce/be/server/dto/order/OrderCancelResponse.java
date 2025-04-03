package kr.ecommerce.be.server.dto.order;

public record OrderCancelResponse(
        Long orderId,
        int status
) {}

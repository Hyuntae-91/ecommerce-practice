package kr.ecommerce.be.server.application.point;

public record PointChargeResponse(
        Long userId,
        Long totalPoint
) {}
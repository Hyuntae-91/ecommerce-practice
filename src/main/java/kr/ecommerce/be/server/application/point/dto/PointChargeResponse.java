package kr.ecommerce.be.server.application.point.dto;

public record PointChargeResponse(
        Long userId,
        Long totalPoint
) {}
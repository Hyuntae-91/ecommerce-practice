package kr.ecommerce.be.server.dto.point;

public record PointChargeResponse (
        Long userId,
        Long total_point
) {}

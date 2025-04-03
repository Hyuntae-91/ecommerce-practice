package kr.ecommerce.be.server.dto.point;

public record PointHistory(
        Long userId,
        Long point,
        String type,
        String createdAt
) {}
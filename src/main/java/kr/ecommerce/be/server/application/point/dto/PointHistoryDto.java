package kr.ecommerce.be.server.application.point.dto;

import kr.ecommerce.be.server.domain.point.model.PointHistory;

public record PointHistoryDto (
        Long userId,
        Long point,
        String type,
        String createdAt
) {
    public static PointHistoryDto from(PointHistory entity) {
        return new PointHistoryDto(
                entity.getUserId(),
                entity.getPoint(),
                entity.getType().name(),
                entity.getCreatedAt()
        );
    }
}
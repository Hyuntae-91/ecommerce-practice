package kr.ecommerce.be.server.infrastructure.point.dto;

import kr.ecommerce.be.server.domain.point.model.PointHistoryType;

public record SavePointHistoryRepoRequestDto (
        Long userId,
        Long point,
        PointHistoryType type
){}

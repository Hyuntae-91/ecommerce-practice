package kr.ecommerce.be.server.interfaces.api.point.dto.response;

import kr.ecommerce.be.server.domain.point.dto.response.PointHistoryServiceResponse;

public record PointHistory (
        Long userId,
        Long point,
        String type,
        String createdAt
) {
    public static PointHistory from(PointHistoryServiceResponse dto) {
        return new PointHistory(dto.userId(), dto.point(), dto.type(), dto.createdAt());
    }
}

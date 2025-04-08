package kr.ecommerce.be.server.interfaces.api.point.dto;

import kr.ecommerce.be.server.domain.point.dto.UserPointResponseDto;

public record PointResponse(
        Long userId,
        Long point
) {
    public static PointResponse from(UserPointResponseDto dto) {
        return new PointResponse(dto.userId(), dto.point());
    }
}
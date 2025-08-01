package kr.ecommerce.be.server.interfaces.api.point.dto.response;

import kr.ecommerce.be.server.domain.point.dto.response.UserPointServiceResponse;

public record PointResponse(
        Long userId,
        Long point
) {
    public static PointResponse from(UserPointServiceResponse dto) {
        return new PointResponse(dto.userId(), dto.point());
    }
}
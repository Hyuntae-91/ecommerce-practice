package kr.ecommerce.be.server.interfaces.api.point.dto;

import kr.ecommerce.be.server.domain.point.dto.PointHistoryResponseDto;

import java.util.List;

public record PointHistoryResponse(
        List<PointHistoryResponseDto> history
) {}

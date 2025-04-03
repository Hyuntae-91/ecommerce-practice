package kr.ecommerce.be.server.dto.point;

import java.util.List;

public record PointHistoryResponse(
        List<PointHistory> history
) {}

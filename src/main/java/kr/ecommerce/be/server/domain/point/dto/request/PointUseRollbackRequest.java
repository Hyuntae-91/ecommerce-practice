package kr.ecommerce.be.server.domain.point.dto.request;

public record PointUseRollbackRequest (
        Long userId,
        Long point
) {
    public PointUseRollbackRequest {
        if (userId == null || userId < 1) {
            throw new IllegalArgumentException("userId는 1 이상이어야 합니다.");
        }
        if (point == null || point <= 0) {
            throw new IllegalArgumentException("사용 포인트는 0보다 커야 합니다.");
        }
    }
}
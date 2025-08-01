package kr.ecommerce.be.server.domain.order.dto.request;

public record GetCartServiceRequest (
        Long userId
) {
    public GetCartServiceRequest {
        if (userId == null || userId < 1) {
            throw new IllegalArgumentException("userId는 1 이상이어야 합니다.");
        }
    }
}

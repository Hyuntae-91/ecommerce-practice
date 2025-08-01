package kr.ecommerce.be.server.domain.order.dto.event;

import kr.ecommerce.be.server.domain.product.dto.request.ProductOptionKeyDto;

import java.util.List;

public record OrderCreatedEvent(
        Long orderId,
        Long userId,
        Long couponId,
        Long couponIssueId,
        List<ProductOptionKeyDto> items
) {
    public OrderCreatedEvent {
        if (orderId == null || orderId < 1) {
            throw new IllegalArgumentException("orderId는 1 이상이어야 합니다.");
        }
        if (userId == null || userId < 1) {
            throw new IllegalArgumentException("userId는 1 이상이어야 합니다.");
        }
        if (couponId != null && couponId < 1) {
            throw new IllegalArgumentException("couponId는 1 이상이어야 합니다.");
        }
        if (couponIssueId != null && couponIssueId < 1) {
            throw new IllegalArgumentException("couponIssueId는 1 이상이어야 합니다.");
        }
        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("items는 null이거나 비어 있을 수 없습니다.");
        }
    }
}

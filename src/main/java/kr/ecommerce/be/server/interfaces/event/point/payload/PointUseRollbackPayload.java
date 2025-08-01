package kr.ecommerce.be.server.interfaces.event.point.payload;

import kr.ecommerce.be.server.interfaces.event.product.payload.ProductDataIds;

import java.util.List;

public record PointUseRollbackPayload (
        Long orderId,
        Long userId,
        Long couponId,
        Long couponIssueId,
        List<ProductDataIds> items
){
    public PointUseRollbackPayload {
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

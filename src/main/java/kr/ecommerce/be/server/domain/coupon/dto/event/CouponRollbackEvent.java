package kr.ecommerce.be.server.domain.coupon.dto.event;

public record CouponRollbackEvent(Long userId, Long couponIssueId) {
}
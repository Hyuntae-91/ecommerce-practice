package kr.ecommerce.be.server.domain.coupon.dto.response;

import kr.ecommerce.be.server.domain.coupon.model.Coupon;

public record CouponDto(
        Long id,
        Long issueId,
        String type,
        String description,
        Integer discount,
        Integer expirationDays
) {
    public CouponDto {
        if (id == null || id < 1) {
            throw new IllegalArgumentException("id는 1 이상이어야 합니다.");
        }
        if (issueId == null || issueId < 1) {
            throw new IllegalArgumentException("id는 1 이상이어야 합니다.");
        }
        if (type == null || type.isBlank()) {
            throw new IllegalArgumentException("type은 필수입니다.");
        }
        if (discount == null || discount < 0) {
            throw new IllegalArgumentException("discount는 0 이상이어야 합니다.");
        }
        if (expirationDays == null || expirationDays < 1) {
            throw new IllegalArgumentException("expirationDays는 1 이상이어야 합니다.");
        }
    }

    public static CouponDto from(Coupon coupon, Long issueId) {
        return new CouponDto(
                coupon.getId(),
                issueId,
                coupon.getType().name(),
                coupon.getDescription(),
                coupon.getDiscount(),
                coupon.getExpirationDays()
        );
    }
}
package kr.ecommerce.be.server.interfaces.api.coupon.dto;

import kr.ecommerce.be.server.domain.coupon.dto.response.GetCouponsServiceResponse;

import java.util.List;

public record CouponListResponse(
        List<CouponResponse> coupons
) {
    public static CouponListResponse from(GetCouponsServiceResponse serviceResponse) {
        List<CouponResponse> responses = serviceResponse.coupons().stream()
                .map(coupon -> new CouponResponse(
                        coupon.id(),
                        coupon.issueId(),
                        coupon.discount(),
                        coupon.description(),
                        coupon.expirationDays()
                ))
                .toList();

        return new CouponListResponse(responses);
    }
}

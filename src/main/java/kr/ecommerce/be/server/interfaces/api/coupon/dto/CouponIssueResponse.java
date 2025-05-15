package kr.ecommerce.be.server.interfaces.api.coupon.dto;

import kr.ecommerce.be.server.domain.coupon.dto.response.IssueNewCouponServiceResponse;

public record CouponIssueResponse(
        Long couponId,
        String type,
        String description,
        Long discount
) {
    public static CouponIssueResponse from(IssueNewCouponServiceResponse serviceDto) {
        return new CouponIssueResponse(
                serviceDto.couponId(),
                serviceDto.type(),
                serviceDto.description(),
                serviceDto.discount().longValue()  // int → long
        );
    }
}

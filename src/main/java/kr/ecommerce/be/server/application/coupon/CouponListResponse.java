package kr.ecommerce.be.server.application.coupon;

import java.util.List;

public record CouponListResponse(
        List<CouponResponse> coupons
) {}

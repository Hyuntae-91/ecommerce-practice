package kr.ecommerce.be.server.domain.coupon.dto.response;

import java.util.List;

public record GetCouponsServiceResponse(
        List<CouponDto> coupons
) {
}

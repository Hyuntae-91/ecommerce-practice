package kr.ecommerce.be.server.domain.coupon;

import kr.ecommerce.be.server.domain.coupon.model.Coupon;

public interface CouponRepository {
    Coupon findById(Long couponId);
    Coupon save(Coupon coupon);
}

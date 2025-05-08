package kr.ecommerce.be.server.domain.coupon.repository;

import kr.ecommerce.be.server.domain.coupon.model.Coupon;

public interface CouponRepository {
    Coupon findById(Long couponId);
    Coupon findWithLockById(Long couponId);
    Coupon save(Coupon coupon);
}

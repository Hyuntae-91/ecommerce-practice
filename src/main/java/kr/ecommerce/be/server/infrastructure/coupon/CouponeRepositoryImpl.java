package kr.ecommerce.be.server.infrastructure.coupon;

import kr.ecommerce.be.server.domain.coupon.CouponRepository;
import kr.ecommerce.be.server.domain.coupon.model.Coupon;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CouponeRepositoryImpl implements CouponRepository {

    @Override
    public Coupon findById(Long couponId) { return null; }

    @Override
    public Coupon save(Coupon coupon) { return coupon; }
}

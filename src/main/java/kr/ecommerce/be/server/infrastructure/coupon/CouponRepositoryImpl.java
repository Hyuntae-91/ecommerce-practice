package kr.ecommerce.be.server.infrastructure.coupon;

import kr.ecommerce.be.server.domain.coupon.repository.CouponRepository;
import kr.ecommerce.be.server.domain.coupon.model.Coupon;
import kr.ecommerce.be.server.exception.custom.ResourceNotFoundException;
import kr.ecommerce.be.server.infrastructure.coupon.repository.CouponJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CouponRepositoryImpl implements CouponRepository {

    private final CouponJpaRepository couponJpaRepository;

    @Override
    public Coupon findById(Long couponId) {
        return couponJpaRepository.findById(couponId)
                .orElseThrow(() -> new ResourceNotFoundException("존재하지 않는 쿠폰입니다."));
    }

    @Override
    public Coupon findWithLockById(Long couponId) {
        return couponJpaRepository.findWithLockById(couponId)
                .orElseThrow(() -> new ResourceNotFoundException("존재하지 않는 쿠폰입니다."));
    }

    @Override
    public Coupon save(Coupon coupon) {
        return couponJpaRepository.save(coupon);
    }

    @Override
    public List<Coupon> findActiveCoupons() {
        return couponJpaRepository.findByState(1);
    }
}
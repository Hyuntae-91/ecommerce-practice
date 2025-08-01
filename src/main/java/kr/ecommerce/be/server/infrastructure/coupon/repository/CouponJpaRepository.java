package kr.ecommerce.be.server.infrastructure.coupon.repository;

import jakarta.persistence.LockModeType;
import kr.ecommerce.be.server.domain.coupon.model.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CouponJpaRepository extends JpaRepository<Coupon, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Coupon> findWithLockById(@Param("couponId") Long couponId);

    List<Coupon> findByState(int state);
}

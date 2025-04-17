package kr.ecommerce.be.server.infrastructure.point.repository;

import kr.ecommerce.be.server.domain.point.model.UserPoint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPointJpaRepository extends JpaRepository<UserPoint, Long> {
}
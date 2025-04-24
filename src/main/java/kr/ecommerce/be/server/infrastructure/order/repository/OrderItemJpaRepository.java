package kr.ecommerce.be.server.infrastructure.order.repository;

import jakarta.persistence.LockModeType;
import kr.ecommerce.be.server.domain.order.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.util.List;

public interface OrderItemJpaRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findAllByOrderId(Long orderId);

    List<OrderItem> findCartByUserId(Long userId);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    List<OrderItem> findAllByIdIn(List<Long> ids);
}

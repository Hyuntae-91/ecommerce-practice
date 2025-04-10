package kr.ecommerce.be.server.infrastructure.order.repository;

import kr.ecommerce.be.server.domain.order.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemJpaRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findAllByOrderId(Long orderId);
    List<OrderItem> findCartByUserId(Long userId);
}

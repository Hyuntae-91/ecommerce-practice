package kr.ecommerce.be.server.infrastructure.order;

import kr.ecommerce.be.server.domain.order.OrderItemRepository;
import kr.ecommerce.be.server.domain.order.model.OrderItem;
import kr.ecommerce.be.server.infrastructure.order.repository.OrderItemJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderItemRepositoryImpl implements OrderItemRepository {

    private final OrderItemJpaRepository orderItemJpaRepository;

    @Override
    public OrderItem save(OrderItem orderItem) {
        return orderItemJpaRepository.save(orderItem);
    }

    @Override
    public List<OrderItem> findAllByOrderId(Long orderId) {
        return orderItemJpaRepository.findAllByOrderId(orderId);
    }

    @Override
    public List<OrderItem> findCartByUserId(Long userId) {
        return orderItemJpaRepository.findCartByUserId(userId);
    }

    @Override
    public void saveAll(List<OrderItem> orderItems) {
        orderItemJpaRepository.saveAll(orderItems);
    }
}

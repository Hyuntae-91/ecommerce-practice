package kr.ecommerce.be.server.infrastructure.order;

import kr.ecommerce.be.server.domain.order.repository.OrderItemRepository;
import kr.ecommerce.be.server.domain.order.model.OrderItem;
import kr.ecommerce.be.server.exception.custom.ResourceNotFoundException;
import kr.ecommerce.be.server.infrastructure.order.repository.OrderItemJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderItemRepositoryImpl implements OrderItemRepository {

    private final OrderItemJpaRepository orderItemJpaRepository;

    @Override
    public OrderItem findById(Long id) {
        return orderItemJpaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("OrderItem not found: " + id));
    }

    @Override
    public OrderItem save(OrderItem orderItem) {
        return orderItemJpaRepository.save(orderItem);
    }

    @Override
    public List<OrderItem> findByIds(List<Long> orderItemIds) {
        return orderItemJpaRepository.findAllById(orderItemIds);
    }

    @Override
    public List<OrderItem> findCartByUserId(Long userId) {
        return orderItemJpaRepository.findCartByUserId(userId);
    }

    @Override
    public List<OrderItem> findCartByUserIdAndOptionIds(Long userId, List<Long> optionIds) {
        return orderItemJpaRepository.findByUserIdAndOptionIdIn(userId, optionIds);
    }

    @Override
    public List<OrderItem> saveAll(List<OrderItem> orderItems) {
        return orderItemJpaRepository.saveAll(orderItems);
    }
}

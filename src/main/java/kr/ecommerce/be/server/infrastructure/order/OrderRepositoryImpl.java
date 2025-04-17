package kr.ecommerce.be.server.infrastructure.order;

import kr.ecommerce.be.server.exception.custom.ResourceNotFoundException;
import kr.ecommerce.be.server.domain.order.repository.OrderRepository;
import kr.ecommerce.be.server.domain.order.model.Order;
import kr.ecommerce.be.server.infrastructure.order.repository.OrderJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepository {

    private final OrderJpaRepository orderJpaRepository;

    @Override
    public Order save(Order order) {
        return orderJpaRepository.save(order);
    }

    @Override
    public Order getById(Long id) {
        return orderJpaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order Not Found"));
    }
}

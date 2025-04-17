package kr.ecommerce.be.server.domain.order.repository;

import kr.ecommerce.be.server.domain.order.model.Order;

public interface OrderRepository {
    Order save(Order order);

    Order getById(Long id);
}

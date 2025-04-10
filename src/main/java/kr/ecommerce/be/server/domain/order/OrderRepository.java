package kr.ecommerce.be.server.domain.order;

import kr.ecommerce.be.server.domain.order.model.Order;

public interface OrderRepository {
    Order save(Order order);

    Order getById(Long id);
}

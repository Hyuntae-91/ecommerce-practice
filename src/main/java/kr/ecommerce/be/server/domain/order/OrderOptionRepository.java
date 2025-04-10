package kr.ecommerce.be.server.domain.order;

import kr.ecommerce.be.server.domain.order.model.OrderOption;

public interface OrderOptionRepository {
    OrderOption getById(Long id);
    void save(OrderOption orderOption);
}

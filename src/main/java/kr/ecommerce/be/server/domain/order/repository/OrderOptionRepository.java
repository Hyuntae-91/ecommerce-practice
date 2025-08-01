package kr.ecommerce.be.server.domain.order.repository;

import kr.ecommerce.be.server.domain.order.model.OrderOption;

import java.util.List;

public interface OrderOptionRepository {
    OrderOption findWithLockById(Long id);
    OrderOption save(OrderOption orderOption);
    List<OrderOption> findByProductId(Long productId);
    OrderOption findById(Long optionId);
}

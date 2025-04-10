package kr.ecommerce.be.server.infrastructure.order;

import kr.ecommerce.be.server.domain.order.OrderOptionRepository;
import kr.ecommerce.be.server.domain.order.model.OrderOption;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderOptionRepositoryImpl  implements OrderOptionRepository {
    @Override
    public OrderOption getById(Long id) {
        return OrderOption.builder().id(id).build();
    }

    @Override
    public void save(OrderOption orderOption) {}
}

package kr.ecommerce.be.server.domain.order.service;

import kr.ecommerce.be.server.domain.order.model.OrderItem;
import kr.ecommerce.be.server.domain.order.repository.OrderItemRepository;
import kr.ecommerce.be.server.domain.product.dto.request.ProductOptionKeyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;

    public long calculateTotalPrice(List<ProductOptionKeyDto> keyDtos) {
        long total = 0L;
        for (ProductOptionKeyDto dto : keyDtos) {
            OrderItem item = orderItemRepository.findById(dto.itemId());
            total += item.calculateTotalPrice();
        }
        return total;
    }
}

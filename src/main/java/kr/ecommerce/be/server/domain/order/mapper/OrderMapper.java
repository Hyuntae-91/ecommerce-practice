package kr.ecommerce.be.server.domain.order.mapper;

import kr.ecommerce.be.server.domain.order.dto.request.*;
import kr.ecommerce.be.server.domain.order.model.OrderItem;
import kr.ecommerce.be.server.domain.payment.dto.event.PaymentCompletedEvent;
import kr.ecommerce.be.server.domain.product.dto.request.ProductOptionKeyDto;
import kr.ecommerce.be.server.interfaces.api.payment.dto.request.PaymentRequest;
import kr.ecommerce.be.server.interfaces.event.coupon.payload.CouponUseRollbackPayload;
import kr.ecommerce.be.server.interfaces.event.payment.payload.PaymentCompletedPayload;
import kr.ecommerce.be.server.interfaces.event.product.payload.ProductDataIds;
import kr.ecommerce.be.server.interfaces.event.product.payload.ProductTotalPriceFailRollbackPayload;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    default CreateOrderServiceRequest toServiceRequest(Long userId, PaymentRequest request) {
        List<CreateOrderOptionDto> options = request.products().stream()
                .map(p -> new CreateOrderOptionDto(p.optionId(), p.quantity()))
                .toList();

        return new CreateOrderServiceRequest(userId, request.couponId(), request.couponIssueId(), options);
    }

    default List<ProductDataIds> toProductDataIds(List<OrderItem> dtoList) {
        return dtoList.stream()
                .map(item -> new ProductDataIds(
                        item.getProductId(),
                        item.getOptionId(),
                        item.getId(),
                        item.getQuantity()
                ))
                .toList();
    }

    UpdateOrderServiceRequest toUpdateOrderRequest(PaymentCompletedPayload event);

    UpdateOrderStateRequest toUpdateOrderStateRequest(CouponUseRollbackPayload event, Integer state);

    OrderStockRollbackRequest toOrderStockRollbackRequest(CouponUseRollbackPayload event);
}
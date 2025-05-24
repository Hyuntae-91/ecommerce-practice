package kr.ecommerce.be.server.domain.coupon.mapper;

import kr.ecommerce.be.server.domain.coupon.dto.event.ApplyCouponDiscountCompletedEvent;
import kr.ecommerce.be.server.domain.coupon.dto.request.ApplyCouponDiscountServiceRequest;
import kr.ecommerce.be.server.domain.coupon.dto.response.ApplyCouponDiscountServiceResponse;
import kr.ecommerce.be.server.domain.product.dto.event.ProductTotalPriceCompletedEvent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CouponMapper {
    @Mapping(source = "totalPrice", target = "originalPrice")
    ApplyCouponDiscountServiceRequest toApplyCouponDiscountServiceRequest(ProductTotalPriceCompletedEvent event);

    default ApplyCouponDiscountCompletedEvent toApplyCouponDiscountCompletedEvent(
            ProductTotalPriceCompletedEvent event,
            ApplyCouponDiscountServiceResponse response
    ) {
        return new ApplyCouponDiscountCompletedEvent(
                event.orderId(),
                event.userId(),
                event.couponId(),
                response.finalPrice(),
                event.productIds()
        );
    }
}


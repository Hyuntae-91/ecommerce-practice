package kr.ecommerce.be.server.domain.coupon.mapper;

import kr.ecommerce.be.server.domain.coupon.dto.event.ApplyCouponDiscountCompletedEvent;
import kr.ecommerce.be.server.domain.coupon.dto.request.ApplyCouponDiscountServiceRequest;
import kr.ecommerce.be.server.domain.coupon.dto.request.CouponUseRequest;
import kr.ecommerce.be.server.domain.coupon.dto.response.ApplyCouponDiscountServiceResponse;
import kr.ecommerce.be.server.domain.product.dto.event.ProductTotalPriceCompletedEvent;
import kr.ecommerce.be.server.interfaces.event.coupon.payload.CouponApplyCompletedPayload;
import kr.ecommerce.be.server.interfaces.event.coupon.payload.CouponUseRollbackPayload;
import kr.ecommerce.be.server.interfaces.event.point.payload.PointUseRollbackPayload;
import kr.ecommerce.be.server.interfaces.event.product.payload.ProductTotalPriceCompletedPayload;
import kr.ecommerce.be.server.interfaces.event.product.payload.ProductTotalPriceFailRollbackPayload;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CouponMapper {
    @Mapping(source = "totalPrice", target = "originalPrice")
    ApplyCouponDiscountServiceRequest toApplyCouponDiscountServiceRequest(ProductTotalPriceCompletedPayload event);

    default CouponApplyCompletedPayload toCouponApplyCompletedPayload(
            ProductTotalPriceCompletedPayload event,
            ApplyCouponDiscountServiceResponse response
    ) {
        return new CouponApplyCompletedPayload(
                event.orderId(),
                event.userId(),
                event.couponId(),
                event.couponIssueId(),
                response.finalPrice(),
                event.items()
        );
    }

    CouponUseRequest toCouponUseRequest(PointUseRollbackPayload event, int state);
    CouponUseRollbackPayload toProductTotalPriceFailRollbackPayload(PointUseRollbackPayload event);
    CouponUseRollbackPayload toProductTotalPriceFailRollbackPayload(ProductTotalPriceCompletedPayload event);
}


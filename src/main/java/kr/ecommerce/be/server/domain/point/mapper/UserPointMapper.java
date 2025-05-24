package kr.ecommerce.be.server.domain.point.mapper;

import kr.ecommerce.be.server.domain.coupon.dto.event.ApplyCouponDiscountCompletedEvent;
import kr.ecommerce.be.server.domain.point.dto.event.PointUsedCompletedEvent;
import kr.ecommerce.be.server.domain.point.dto.request.PointUseServiceRequest;
import kr.ecommerce.be.server.domain.point.dto.response.PointChargeServiceResponse;
import kr.ecommerce.be.server.domain.point.dto.response.PointHistoryServiceResponse;
import kr.ecommerce.be.server.domain.point.dto.response.PointUseServiceResponse;
import kr.ecommerce.be.server.domain.point.dto.response.UserPointServiceResponse;
import kr.ecommerce.be.server.domain.point.model.PointHistory;
import kr.ecommerce.be.server.domain.point.model.UserPoint;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserPointMapper {

    UserPointMapper INSTANCE = Mappers.getMapper(UserPointMapper.class);

    UserPointServiceResponse toUserPointResponse(UserPoint userPoint);

    PointChargeServiceResponse toUserPointChargeResponse(UserPoint userPoint);

    PointUseServiceResponse toUserPointUseResponse(UserPoint userPoint);

    PointHistoryServiceResponse toUserPointHistoryResponse(PointHistory pointHistory);

    List<PointHistoryServiceResponse> toHistoryListResponse(List<PointHistory> pointHistories);

    default PointUseServiceRequest toPointUseServiceRequest(ApplyCouponDiscountCompletedEvent event) {
        return new PointUseServiceRequest(event.userId(), event.finalPrice());
    }

    default PointUsedCompletedEvent toPointUsedCompletedEvent(
            ApplyCouponDiscountCompletedEvent event,
            PointUseServiceResponse response
    ) {
        return new PointUsedCompletedEvent(
                event.orderId(),
                event.userId(),
                response.point(),
                event.productIds()
        );
    }
}

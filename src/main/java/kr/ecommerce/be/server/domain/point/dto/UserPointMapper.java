package kr.ecommerce.be.server.domain.point.dto;

import kr.ecommerce.be.server.domain.point.dto.response.PointChargeServiceResponse;
import kr.ecommerce.be.server.domain.point.dto.response.PointHistoryServiceResponse;
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

    PointHistoryServiceResponse toUserPointHistoryResponse(PointHistory pointHistory);

    List<PointHistoryServiceResponse> toHistoryListResponse(List<PointHistory> pointHistories);

}

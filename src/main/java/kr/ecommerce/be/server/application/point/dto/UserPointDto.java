package kr.ecommerce.be.server.application.point.dto;

import kr.ecommerce.be.server.application.product.dto.ProductDto;
import kr.ecommerce.be.server.domain.point.model.UserPoint;
import lombok.Builder;

@Builder
public record UserPointDto (
    Long userId,
    Long point
) {

    public static UserPointDto from(UserPoint userPoint) {
        return UserPointDto.builder()
                .userId(userPoint.getUserId())
                .point(userPoint.getPoint())
                .build();
    }
}

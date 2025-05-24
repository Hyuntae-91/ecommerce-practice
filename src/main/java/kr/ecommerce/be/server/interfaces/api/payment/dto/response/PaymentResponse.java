package kr.ecommerce.be.server.interfaces.api.payment.dto.response;

import kr.ecommerce.be.server.domain.order.dto.response.CreateOrderServiceResponse;

public record PaymentResponse(
        Long orderId
) {
    public static PaymentResponse from(CreateOrderServiceResponse svcDto) {
        return new PaymentResponse(
                svcDto.orderId()
        );
    }
}


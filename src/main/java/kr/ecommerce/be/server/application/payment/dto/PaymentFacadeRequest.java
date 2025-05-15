package kr.ecommerce.be.server.application.payment.dto;

import java.util.List;

public record PaymentFacadeRequest (
        Long userId,
        List<PaymentProductFacadeDto> products,
        Long couponId
) {}

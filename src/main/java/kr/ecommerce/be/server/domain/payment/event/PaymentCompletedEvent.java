package kr.ecommerce.be.server.domain.payment.event;

import kr.ecommerce.be.server.domain.payment.dto.response.PaymentServiceResponse;

public record PaymentCompletedEvent(PaymentServiceResponse payment) {}
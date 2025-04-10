package kr.ecommerce.be.server.domain.payment.event;

import kr.ecommerce.be.server.domain.payment.dto.PaymentServiceResponse;

public record PaymentCompletedEvent(PaymentServiceResponse payment) {}
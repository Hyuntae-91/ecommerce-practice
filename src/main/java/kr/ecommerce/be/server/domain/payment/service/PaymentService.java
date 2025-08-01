package kr.ecommerce.be.server.domain.payment.service;

import kr.ecommerce.be.server.domain.payment.dto.request.PaymentServiceRequest;
import kr.ecommerce.be.server.domain.payment.dto.response.PaymentServiceResponse;
import kr.ecommerce.be.server.domain.payment.model.Payment;
import kr.ecommerce.be.server.domain.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;

    private PaymentServiceResponse toResponse(Payment payment) {
        return new PaymentServiceResponse(
                payment.getId(),
                payment.getOrderId(),
                payment.getState(),
                payment.getTotalPrice(),
                payment.getCreatedAt()
        );
    }

    public PaymentServiceResponse pay(PaymentServiceRequest request) {
        long totalPrice = request.totalPrice();
        long orderId = request.orderId();

        try {
            // 결제 처리
            Payment payment = Payment.of(orderId, 1, totalPrice);
            Payment savedPayment = paymentRepository.save(payment);
            return toResponse(savedPayment);
        } catch (Exception e) {
            // 결제 실패 처리: status -1
            Payment failedPayment = Payment.of(orderId, -1, totalPrice);
            Payment savedFailed = paymentRepository.save(failedPayment);
            throw e;
        }
    }
}

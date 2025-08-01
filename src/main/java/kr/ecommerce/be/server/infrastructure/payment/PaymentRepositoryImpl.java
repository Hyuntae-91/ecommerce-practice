package kr.ecommerce.be.server.infrastructure.payment;

import kr.ecommerce.be.server.domain.payment.repository.PaymentRepository;
import kr.ecommerce.be.server.domain.payment.model.Payment;
import kr.ecommerce.be.server.exception.custom.ResourceNotFoundException;
import kr.ecommerce.be.server.infrastructure.payment.repository.PaymentJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PaymentRepositoryImpl implements PaymentRepository {

    private final PaymentJpaRepository paymentJpaRepository;

    @Override
    public Payment save(Payment payment) {
        return paymentJpaRepository.save(payment);
    }

    @Override
    public Payment findById(Long id) {
        return paymentJpaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found"));
    }

    @Override
    public List<Payment> findAll() { return paymentJpaRepository.findAll(); }
}

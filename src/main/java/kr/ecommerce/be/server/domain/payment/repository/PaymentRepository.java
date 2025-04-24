package kr.ecommerce.be.server.domain.payment.repository;

import kr.ecommerce.be.server.domain.payment.model.Payment;

import java.util.List;

public interface PaymentRepository {

    Payment save(Payment payment);

    Payment findById(Long id);

    List<Payment> findAll();
}

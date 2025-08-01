package kr.ecommerce.be.server.domain.product.event;

import kr.ecommerce.be.server.domain.payment.dto.event.PaymentCompletedEvent;
import kr.ecommerce.be.server.domain.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductScoreUpdateEventListener {

    private final ProductService productService;

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handleProductSoldEvent(PaymentCompletedEvent event) {
        productService.updateProductsScore(event.productIds());
    }
}

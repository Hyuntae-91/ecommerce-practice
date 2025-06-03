package kr.ecommerce.be.server.domain.payment.mapper;

import kr.ecommerce.be.server.domain.payment.dto.request.PaymentServiceRequest;
import kr.ecommerce.be.server.domain.payment.dto.response.PaymentServiceResponse;
import kr.ecommerce.be.server.domain.product.dto.request.ProductOptionKeyDto;
import kr.ecommerce.be.server.interfaces.event.payment.payload.PaymentCompletedPayload;
import kr.ecommerce.be.server.interfaces.event.payment.payload.PaymentRollbackPayload;
import kr.ecommerce.be.server.interfaces.event.point.payload.PointUsedCompletedPayload;
import kr.ecommerce.be.server.interfaces.event.product.payload.ProductDataIds;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    default PaymentServiceRequest toPaymentServiceRequest(PointUsedCompletedPayload event) {
        return new PaymentServiceRequest(
                event.userId(),
                event.usedPoint(),
                event.orderId()
        );
    }

    default PaymentCompletedPayload toPaymentCompletedPayload(PaymentServiceResponse response, PointUsedCompletedPayload event) {
        List<Long> productIds = event.items().stream()
                .map(ProductDataIds::productId)
                .distinct()
                .toList();
        return new PaymentCompletedPayload(
                response.paymentId(),
                response.orderId(),
                response.status(),
                response.totalPrice(),
                response.createdAt(),
                productIds
        );
    }

    PaymentRollbackPayload toPaymentRollbackPayload(PointUsedCompletedPayload event);
}

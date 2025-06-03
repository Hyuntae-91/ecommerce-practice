package kr.ecommerce.be.server.interfaces.event.point;

import kr.ecommerce.be.server.application.publisher.MessagePublisher;
import kr.ecommerce.be.server.common.constants.Groups;
import kr.ecommerce.be.server.common.constants.Topics;
import kr.ecommerce.be.server.domain.point.mapper.UserPointMapper;
import kr.ecommerce.be.server.domain.point.service.PointService;
import kr.ecommerce.be.server.interfaces.event.payment.payload.PaymentRollbackPayload;
import kr.ecommerce.be.server.interfaces.event.point.payload.PointUseRollbackPayload;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class PointUseRollbackProcessor {

    private final PointService pointService;
    private final UserPointMapper userPointMapper;
    private final MessagePublisher<PointUseRollbackPayload> pointUseRollbackPayloadPublisher;

    @KafkaListener(topics = Topics.PAYMENT_FAILED_TOPIC, groupId = Groups.PAYMENT_FAILED_GROUP)
    public void consume(@Payload PaymentRollbackPayload event) {
        pointService.useRollback(userPointMapper.toPointUseRollbackRequest(event));
        pointUseRollbackPayloadPublisher.publish(
                Topics.USE_USER_POINT_FAILED_TOPIC,
                null,
                userPointMapper.toPointUseRollbackPayload(event)
        );
    }
}

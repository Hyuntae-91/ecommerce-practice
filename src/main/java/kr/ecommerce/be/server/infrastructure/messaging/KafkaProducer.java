package kr.ecommerce.be.server.infrastructure.messaging;

import kr.ecommerce.be.server.application.publisher.MessagePublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;

@RequiredArgsConstructor
public class KafkaProducer<T> implements MessagePublisher<T> {
    private final KafkaTemplate<String, T> kafkaTemplate;

    @Override
    public void publish(String topic, String key, T message) {
        if (key != null) {
            kafkaTemplate.send(topic, key, message);
        } else {
            kafkaTemplate.send(topic, message);
        }
    }
}

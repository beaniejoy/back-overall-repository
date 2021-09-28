package io.beaniejoy.springmessageqkafka.producer.message;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaProducer {
    public static final String TOPIC_NAME = "mygroup";

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void send(String topic, Object event) {
        log.info(topic + " : " + event);
        kafkaTemplate.send(TOPIC_NAME, event);
    }
}

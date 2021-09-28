package io.beaniejoy.springmessageqkafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import static io.beaniejoy.springmessageqkafka.producer.message.KafkaProducer.TOPIC_NAME;

@Slf4j
@Component
public class KafkaConsumer {
    @KafkaListener(topics = TOPIC_NAME ,autoStartup = "true")
    public void handleEvent(Object event) {
        log.info(event.toString());
    }
}

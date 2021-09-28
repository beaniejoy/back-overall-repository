package io.beaniejoy.springmessageqkafka.producer.service;

import io.beaniejoy.springmessageqkafka.common.domain.MyData;
import io.beaniejoy.springmessageqkafka.common.domain.MyEvent;
import io.beaniejoy.springmessageqkafka.producer.message.KafkaProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyEventService {

    private final KafkaProducer kafkaProducer;

    public void publishEvent() {
        int index = 0;
        while (index < 10) {
            MyEvent myEvent = MyEvent.builder()
                    .data(MyData.builder()
                            .dataId((long) index)
                            .content(index + "번째 data")
                            .build())
                    .build();

            kafkaProducer.send(KafkaProducer.TOPIC_NAME, myEvent);
            index++;
        }

    }
}

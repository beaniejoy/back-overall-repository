package io.beaniejoy.resetpwdemo.outbox.event;

import io.beaniejoy.resetpwdemo.outbox.entity.OutBox;
import io.beaniejoy.resetpwdemo.outbox.entity.OutBoxRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OutBoxEventHandler {
    private final OutBoxRepository outBoxRepository;

    @EventListener
    public void handleOutBoxEvent(OutBoxEvent event) {

        outBoxRepository.save(OutBox.builder()
                .aggregateId(event.getAggregateId())
                .aggregateType(event.getAggregateType())
                .eventType(event.getEventType())
                .payload(event.getPayload())
                .build());

    }
}

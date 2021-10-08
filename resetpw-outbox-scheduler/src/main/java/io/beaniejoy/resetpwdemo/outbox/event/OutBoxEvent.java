package io.beaniejoy.resetpwdemo.outbox.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OutBoxEvent {

    private Long aggregateId;

    private String aggregateType;

    private String eventType;

    private String payload;
}

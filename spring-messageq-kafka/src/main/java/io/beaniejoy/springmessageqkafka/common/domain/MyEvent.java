package io.beaniejoy.springmessageqkafka.common.domain;

import lombok.*;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MyEvent {

    private UUID eventId;

    private MyData data;

    private LocalDateTime createdAt;

    @Builder
    public MyEvent(MyData data) {
        Assert.notNull(data, "data must not be null");

        this.eventId = UUID.randomUUID();
        this.data = data;
        this.createdAt = LocalDateTime.now();
    }
}

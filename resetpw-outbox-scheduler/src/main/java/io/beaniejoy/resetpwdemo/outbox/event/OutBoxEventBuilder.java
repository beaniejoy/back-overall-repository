package io.beaniejoy.resetpwdemo.outbox.event;

public interface OutBoxEventBuilder<T> {
    OutBoxEvent createOutBoxEvent(T domainEvent);
}

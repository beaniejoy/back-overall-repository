package io.beaniejoy.resetpwdemo.outbox.message.mail;

import io.beaniejoy.resetpwdemo.outbox.dto.ContentDto;

public interface ContentService {
    ContentDto createContent(String payload);
}

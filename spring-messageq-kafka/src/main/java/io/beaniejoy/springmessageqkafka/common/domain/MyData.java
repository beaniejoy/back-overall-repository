package io.beaniejoy.springmessageqkafka.common.domain;

import lombok.*;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MyData {

    private Long dataId;

    private String content;
}

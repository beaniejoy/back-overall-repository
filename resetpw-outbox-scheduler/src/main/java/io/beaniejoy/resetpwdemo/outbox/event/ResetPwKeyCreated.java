package io.beaniejoy.resetpwdemo.outbox.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResetPwKeyCreated {

    private Long resetPwKeyId;

    private String resetKey;

    private LocalDateTime expireDate;

    private String email;

    private String userName;
}

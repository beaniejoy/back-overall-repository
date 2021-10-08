package io.beaniejoy.resetpwdemo.core.service;

import io.beaniejoy.resetpwdemo.core.domain.ResetPwKey;
import io.beaniejoy.resetpwdemo.core.repository.ResetPwKeyRepository;
import io.beaniejoy.resetpwdemo.outbox.event.OutBoxEvent;
import io.beaniejoy.resetpwdemo.outbox.event.OutBoxEventBuilder;
import io.beaniejoy.resetpwdemo.outbox.event.ResetPwKeyCreated;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserPasswordResetService {

    private final ResetPwKeyRepository resetPwKeyRepository;

    private final ApplicationEventPublisher eventPublisher;

    private final OutBoxEventBuilder<ResetPwKeyCreated> eventBuilder;

    @Transactional
    public void saveResetKey(String email, String userName) {
        String key = createResetKey();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expiredTime = now.plusMinutes(20);    // 20 minutes set for expire time
        ResetPwKey resetPwKey = toResetPwKeyEntity(key, expiredTime, email);

        ResetPwKey saved = resetPwKeyRepository.save(resetPwKey);

        // As soon as key data saved, publish saved event
        eventPublisher.publishEvent(
                eventBuilder.createOutBoxEvent(ResetPwKeyCreated.builder()
                        .resetPwKeyId(saved.getId())
                        .resetKey(saved.getResetKey())
                        .expireDate(saved.getExpiredAt())
                        .userName(userName)
                        .build())
        );
    }

    private String createResetKey() {
        return UUID.randomUUID().toString();
    }

    private ResetPwKey toResetPwKeyEntity(String key, LocalDateTime expiredAt, String email) {
        return ResetPwKey.builder()
                .resetKey(key)
                .email(email)
                .expiredAt(expiredAt)
                .build();
    }
}

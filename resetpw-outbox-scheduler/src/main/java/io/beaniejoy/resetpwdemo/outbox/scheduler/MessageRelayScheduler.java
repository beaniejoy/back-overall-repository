package io.beaniejoy.resetpwdemo.outbox.scheduler;


import io.beaniejoy.resetpwdemo.outbox.dto.ContentDto;
import io.beaniejoy.resetpwdemo.outbox.entity.OutBox;
import io.beaniejoy.resetpwdemo.outbox.entity.OutBoxRepository;
import io.beaniejoy.resetpwdemo.outbox.message.mail.ContentService;
import io.beaniejoy.resetpwdemo.outbox.message.mail.SendMailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Component
public class MessageRelayScheduler {
    private static Logger logger = LoggerFactory.getLogger(MessageRelayScheduler.class);

    private final OutBoxRepository outBoxRepository;

    private final SendMailService sendMailService;

    private final ContentService mailContentService;

    public MessageRelayScheduler(OutBoxRepository outBoxRepository,
                                 SendMailService sendMailService,
                                 ContentService mailContentService) {
        this.outBoxRepository = outBoxRepository;
        this.sendMailService = sendMailService;
        this.mailContentService = mailContentService;
    }

    /**
     * 5분 주기로 OutBox 테이블 데이터를 기준으로 메일 전송
     *  - OutBox 조회: 모든 OutBox 데이터를 조회
     *  - payload 파싱: OutBox 데이터 중 payload 가지고 MailContent 생성
     *  - 메일 발송: 발송완료되면 완료된 OutBox 리스트에 추가
     *  - OutBox 삭제: 완료된 OutBox 리스트를 기준으로 OutBox 테이블 내 데이터 삭제
     */
    @Transactional
    @Scheduled(cron = "*/10 * * * * *")
    public void schedulingResetPasswordMail() {
        logger.error("Send Mail Scheduler start working");
        List<OutBox> outBoxList = outBoxRepository.findAll();
        if (!outBoxList.isEmpty()) {
            List<Long> outBoxCompletedList = new LinkedList<>();
            outBoxList.forEach(outBox -> {
                String payload = outBox.getPayload();
                ContentDto mailContent = mailContentService.createContent(payload);

                try {
                    sendMailService.sendMail(mailContent);
                    // 발송완료시 완료 목록에 추가
                    outBoxCompletedList.add(outBox.getId());
                } catch (MailException e) {
                    logger.error("Problems in MailSender process");
                }
            });

            if(!outBoxCompletedList.isEmpty())
                outBoxRepository.deleteAllByIdIn(outBoxCompletedList);
        }
    }
}

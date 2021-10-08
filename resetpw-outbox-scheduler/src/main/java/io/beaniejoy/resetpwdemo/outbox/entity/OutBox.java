package io.beaniejoy.resetpwdemo.outbox.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OutBox {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long aggregateId;     // Grouping 위한 id

    private String aggregateType;   // 변경이 발생한 도메인 이름

    private String eventType;       // 발생한 이벤트

    private String payload;         // 도메인 entity 변경사항(JSON)
}

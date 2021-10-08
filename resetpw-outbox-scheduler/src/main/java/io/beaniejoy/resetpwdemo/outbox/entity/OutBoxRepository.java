package io.beaniejoy.resetpwdemo.outbox.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OutBoxRepository extends JpaRepository<OutBox, Long> {
    List<OutBox> findAll();

    void deleteAllByIdIn(List<Long> outBoxIds);
}

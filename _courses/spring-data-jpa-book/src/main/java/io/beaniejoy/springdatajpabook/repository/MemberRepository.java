package io.beaniejoy.springdatajpabook.repository;

import io.beaniejoy.springdatajpabook.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Page<Member> findByNameStartingWith(String name, Pageable pageable);
}

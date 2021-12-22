package io.beaniejoy.springdatajpabook.repository;

import io.beaniejoy.springdatajpabook.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByName(String username);
    List<Member> findByEmailAndName(String email, String name);

    // Member.findAllByName 네임드 쿼리를 실행
    List<Member> findAllByName(@Param("name") String name);

    @Query(value = "select m from Member m where m.name = ?1")
    Member findByNameJpql(String name);

    @Query(value = "SELECT * FROM MEMBER WHERE NAME = ?0", nativeQuery = true)
    Member findByNameNative(String name);

    // count query도 실행됨
    Page<Member> findByNameStartingWith(String name, Pageable pageable);


}

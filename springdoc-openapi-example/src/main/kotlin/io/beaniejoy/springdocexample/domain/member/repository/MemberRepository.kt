package io.beaniejoy.springdocexample.domain.member.repository

import io.beaniejoy.springdocexample.domain.member.entity.Member
import io.beaniejoy.springdocexample.domain.member.entity.Role
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository: JpaRepository<Member, Long> {
    fun findAllByRole(role: Role): List<Member>
}
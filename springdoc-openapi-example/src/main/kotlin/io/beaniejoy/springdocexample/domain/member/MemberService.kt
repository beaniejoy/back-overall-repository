package io.beaniejoy.springdocexample.domain.member

import io.beaniejoy.springdocexample.domain.member.dto.MemberResponseDto
import io.beaniejoy.springdocexample.domain.member.entity.Member
import io.beaniejoy.springdocexample.domain.member.entity.Role
import io.beaniejoy.springdocexample.domain.member.repository.MemberRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class MemberService(
    private val memberRepository: MemberRepository
) {
    @Transactional
    fun register(email: String, password: String): Long {
        val member = Member(
            email = email,
            password = password,
            role = Role.USER
        )

        val savedMember = memberRepository.save(member)

        return savedMember.id
    }

    fun getMemberListWith(role: Role): List<MemberResponseDto> {
        return memberRepository.findAllByRole(role).map {
            MemberResponseDto(
                id = it.id,
                email = it.email,
                role = it.role
            )
        }
    }
}
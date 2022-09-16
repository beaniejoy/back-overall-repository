package io.beaniejoy.junittest.member

import io.beaniejoy.junittest.domain.Member
import io.beaniejoy.junittest.domain.Study
import java.util.Optional

interface MemberService {
    fun findById(memberId: Long): Optional<Member>

    fun validate(memberId: Long)

    fun notify(newStudy: Study)

    fun notify(member: Member)
}
package io.beaniejoy.junittest.study

import io.beaniejoy.junittest.domain.Member
import io.beaniejoy.junittest.domain.Study
import io.beaniejoy.junittest.member.MemberService
import java.util.*


class StudyService(
    private val memberService: MemberService,
    private val repository: StudyRepository,
) {
    fun createNewStudy(memberId: Long, study: Study): Study {
        val member: Optional<Member> = memberService.findById(memberId)
        if (member.isPresent) {
            study.ownerId = memberId
        } else {
            throw IllegalArgumentException("Member doesn't exist for id: '$memberId'")
        }

        val newStudy = repository.save(study)

        memberService.notify(newStudy)

        return newStudy
    }

    fun openStudy(study: Study): Study? {
        study.open()
        val openedStudy = repository.save(study)

        memberService.notify(openedStudy)

        return openedStudy
    }

    fun hi() {}
}
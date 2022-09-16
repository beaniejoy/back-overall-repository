package io.beaniejoy.junittest.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Member protected constructor(
    id: Long,
    email: String?
) {
    @Id
    @GeneratedValue
    val id: Long = id

    var email: String? = email
        protected set

    companion object {
        // 편의상 id 값도 인자로 포함
        fun createMember(id: Long, email: String): Member {
            return Member(id, email)
        }
    }
}
package io.beaniejoy.junittest.domain

import java.time.LocalDateTime
import javax.persistence.*

@Entity
class Study {
    @Id
    @GeneratedValue
    val id: Long = 0L

    var status: StudyStatus = StudyStatus.DRAFT

    var limitCount: Int
        protected set

    var name: String? = null
        protected set

    var openedDateTime: LocalDateTime? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    var owner: Member? = null

    constructor(limitCount: Int, name: String?) {
        this.limitCount = limitCount
        this.name = name
    }

    constructor(limitCount: Int) {
        require(limitCount >= 0) { "limit은 0보다 커야 합니다." }

        this.limitCount = limitCount
    }

    fun open() {
        openedDateTime = LocalDateTime.now()
        status = StudyStatus.OPENED
    }
}
package io.beaniejoy.junittest.study

import io.beaniejoy.junittest.domain.Study
import org.springframework.data.jpa.repository.JpaRepository

interface StudyRepository: JpaRepository<Study, Long> {
}
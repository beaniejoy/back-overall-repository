package io.beaniejoy.junittest.study

import io.beaniejoy.junittest.domain.Study
import org.springframework.web.bind.annotation.*


@RestController
class StudyController(
    private val repository: StudyRepository,
) {
    @GetMapping("/study/{id}")
    fun getStudy(@PathVariable("id") id: Long): Study {
        return repository.findById(id)
            .orElseThrow { IllegalArgumentException("Study not found for '$id'") }
    }

    @PostMapping("/study")
    fun createsStudy(@RequestBody study: Study): Study {
        return repository.save(study)
    }
}
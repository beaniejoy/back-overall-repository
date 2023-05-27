package io.beaniejoy.repository

import io.beaniejoy.entity.MemberBuilder
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MemberRepositoryTest {

    @Test
    fun importDomainModuleTest() {
        val member = MemberBuilder().build()

        assertEquals(member.address, "test address")
    }
}
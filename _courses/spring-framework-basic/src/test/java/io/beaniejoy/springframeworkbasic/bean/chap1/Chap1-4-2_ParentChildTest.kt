package io.beaniejoy.springframeworkbasic.bean.chap1

import io.beaniejoy.springframeworkbasic.bean.chap1.sub4.inherit.Child
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.getBean
import org.springframework.context.support.GenericXmlApplicationContext

@DisplayName("chap 1.4.2 Parent, Child Context 테스트")
class `Chap1-4-2_ParentChildTest` {

    companion object {
        lateinit var parentContext: GenericXmlApplicationContext
        lateinit var childContext: GenericXmlApplicationContext

        @JvmStatic
        @BeforeAll
        fun setUp() {
            parentContext = GenericXmlApplicationContext()
            parentContext.load("chap1/parent.xml")
            parentContext.refresh()

            childContext = GenericXmlApplicationContext()
            childContext.load("chap1/child.xml")
            childContext.parent = parentContext
            childContext.refresh()
        }
    }

    @Test
    @DisplayName("parent context 객체 가져오기 테스트")
    fun beanNamePropertyTest() {
        // alias로 같은 bean을 다른 name으로 지정해서 사용가능
        val child = childContext.getBean<Child>("child")

        assertEquals(child.parent.name, "beanie's parent")
        assertEquals(child.parent.age, 50)
    }
}
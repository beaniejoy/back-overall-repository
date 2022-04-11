package io.beaniejoy.springframeworkbasic.bean.chap1

import io.beaniejoy.springframeworkbasic.bean.chap1.sub4.Outer
import io.beaniejoy.springframeworkbasic.bean.chap1.sub4.inherit.Parent2
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.getBean
import org.springframework.context.ApplicationContext
import org.springframework.context.support.ClassPathXmlApplicationContext

@DisplayName("chap 1.4.2 속성 inheritance 테스트")
class `Chap1-4-2_Test` {
    private val context: ApplicationContext =
        ClassPathXmlApplicationContext("chap1/outer-inner.xml")

    private val context2: ApplicationContext =
        ClassPathXmlApplicationContext("chap1/inherit.xml")

    @Test
    @DisplayName("inner class에 대한 bean 등록 테스트")
    fun outerInnerBeanTest() {
        val outer = context.getBean<Outer>("outer")

        assertEquals(outer.inner.address, "inner's address")
    }

    @Test
    @DisplayName("properties inheritance 테스트")
    fun inheritPropertiesTest() {
        val bean = context2.getBean<Parent2>("child2")

        assertEquals(bean.adminEmails["administrator"], "administrator@example.com")
        assertEquals(bean.adminEmails["sales"], "sales@example.com")
        assertEquals(bean.adminEmails["support"], "support@example.co.uk")  // overriding한 value가 등록
    }
}
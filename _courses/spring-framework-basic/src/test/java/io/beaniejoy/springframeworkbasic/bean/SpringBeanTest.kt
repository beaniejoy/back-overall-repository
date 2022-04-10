package io.beaniejoy.springframeworkbasic.bean

import io.beaniejoy.springframeworkbasic.bean.chap1.sub4.TestBean3
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.getBean
import org.springframework.context.ApplicationContext
import org.springframework.context.support.ClassPathXmlApplicationContext
import java.text.SimpleDateFormat

class SpringBeanTest {
    private val context: ApplicationContext =
        ClassPathXmlApplicationContext("chap1-application.xml")

    @Test
    @DisplayName("bean element의 name 속성을 통해 여러 name 사용 테스트")
    fun beanNamePropertyTest() {
        // alias로 같은 bean을 다른 name으로 지정해서 사용가능
        val name1Bean = context.getBean<TestBean>("name1-bean")
        val name2Bean = context.getBean<TestBean>("name2-bean")

        assertEquals(name1Bean.id, 1L)
        assertEquals(name2Bean.name, "test-bean")
    }

    @Test
    @DisplayName("alias로 하나의 bean으로 여러 name 사용 테스트")
    fun aliasTest() {
        // alias로 같은 bean을 다른 name으로 지정해서 사용가능
        val subABean = context.getBean<TestBean>("subA-bean")
        val subBBean = context.getBean<TestBean>("subB-bean")

        assertEquals(subABean.id, 1L)
        assertEquals(subBBean.name, "test-bean")
    }

    @Test
    @DisplayName("factory method로 만들어진 bean에 대한 singleton 객체 여부 테스트")
    fun factoryMethodSingletonTest() {
        val testBean2A = context.getBean<TestBean2>("testBean2")
        val testBean2B = context.getBean<TestBean2>("testBean2")

        assertTrue(testBean2A === testBean2B)
    }

    @Test
    @DisplayName("1.4.1 DI - PropertyEditor를 통한 property format converting 테스트")
    fun customPropertyEditorTest() {
        val bean = context.getBean<TestBean3>("testBean3")

        println(bean)
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
        val dateStr = dateFormat.format(bean.dateOfBirth)

        assertEquals(dateStr, "2000-09-30")
    }
}
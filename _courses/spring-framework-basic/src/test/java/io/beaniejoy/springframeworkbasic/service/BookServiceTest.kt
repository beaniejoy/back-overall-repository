package io.beaniejoy.springframeworkbasic.service

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.getBean
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader
import org.springframework.context.ApplicationContext
import org.springframework.context.support.ClassPathXmlApplicationContext
import org.springframework.context.support.GenericApplicationContext

class BookServiceTest {

    private val context: ApplicationContext =
        ClassPathXmlApplicationContext("service-application.xml")

    // GenericApplicationContext: ApplicationContext 구현체 중 하나
    private val genericContext = GenericApplicationContext()


    @Test
    @DisplayName("기본적인 ApplicationContext bean 로드 테스트")
    fun findByIdWithAc() {
        val bookService = context.getBean<BookService>("bookService")
        val book = bookService.findBookById(1L)

        assertEquals(book.id, 1L)
    }

    @Test
    @DisplayName("GenericApplicationContext으로 xml 설정 flexible한 bean 로드 테스트")
    fun findByIdWithGenericAc() {
        // xml, groovy 설정 파일을 유연하게 가져올 수 있음
        XmlBeanDefinitionReader(genericContext).loadBeanDefinitions("service-application.xml")
        genericContext.refresh()

        val bookService = genericContext.getBean<BookService>("bookService")
        val book = bookService.findBookById(3L)

        assertEquals(book.id, 3L)
    }
}
package io.beaniejoy.springframeworkbasic.bean

class TestBeanFactory private constructor() {
    companion object {
        private val testBean = TestBeanFactory()

        fun createInstance() = testBean
    }
}
package io.beaniejoy.springframeworkbasic.bean

class TestBeanFactory private constructor() {
    companion object {
        private val testBeanFactory = TestBeanFactory()
        @JvmStatic
        fun createInstance() = testBeanFactory
    }
}

class TestBeanInstanceFactory {
    // 이렇게 xml 설정해도 singleton을 유지하게 된다.
    fun createTestBeanInstance(): TestBean2 {
        return TestBean2("hello", "world")
    }
}
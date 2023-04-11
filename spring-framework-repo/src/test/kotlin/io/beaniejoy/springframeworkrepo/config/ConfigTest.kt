package io.beaniejoy.springframeworkrepo.config

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ConfigTest {
    @Autowired
    lateinit var props: ExampleProps

    @Autowired
    lateinit var props2: ExampleProps2

    @Test
    fun configPropertiesTest() {
        assertThat(props.one).isEqualTo(1)
        assertThat(props.two).isEqualTo(2)
        assertThat(props.text).isEqualTo("text")
        assertThat(props.hello).isEqualTo("hello")
    }

    @Test
    fun configPropertiesConstructorTest() {
        assertThat(props2.one).isEqualTo(1)
        assertThat(props2.two).isEqualTo(2)
        assertThat(props2.text).isEqualTo("text")
        assertThat(props2.hello).isEqualTo("hello")
    }
}
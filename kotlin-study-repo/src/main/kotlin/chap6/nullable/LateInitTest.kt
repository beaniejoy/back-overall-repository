package chap6.nullable

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class MyService {
    fun performAction(): String = "foo"
}

class NotLateInit {
    // nullable 하다.
    private var myService: MyService? = null

    @BeforeEach
    fun setUp() {
        myService = MyService()
    }

    @Test
    fun testAction() {
        // 여기에 null 이 아니라는 확실한 !! 키워드를 넣어야 한다.
        assertEquals("foo", myService!!.performAction())
    }
}

class LateInit {
    // nullable 하다.
    private lateinit var myService: MyService

    @BeforeEach
    fun setUp() {
        myService = MyService()
    }

    @Test
    fun testAction() {
        // 만약 myService 가 초기화되어 있지 않으면 "NPE"가 발생하지 않고
        // lateinit 관련 Exception 이 발생한다.
        // 따로 null 관련한 호출을 하지 않아도 된다.
        assertEquals("foo", myService.performAction())
    }
}
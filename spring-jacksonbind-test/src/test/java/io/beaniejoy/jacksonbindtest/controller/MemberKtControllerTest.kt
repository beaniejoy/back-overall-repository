package io.beaniejoy.jacksonbindtest.controller

import io.beaniejoy.jacksonbindtest.common.JsonKeyManager.*
import mu.KLogging
import org.hamcrest.core.StringContains.containsString
import org.json.JSONObject
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class MemberKtControllerTest {
    companion object: KLogging()

    @Autowired
    private lateinit var mvc: MockMvc

    private var id: Long = 0L
    private var name: String? = null
    private var address: String? = null
    private var email: String? = null

    private var requestJson: JSONObject = JSONObject()

    @BeforeEach
    fun setup() {
        id = 100L;
        name = "beanie";
        address = "beanie's address";
        email = "beanie's email";

        requestJson.put(ID, null)
        requestJson.put(NAME, name)
        requestJson.put(ADDRESS, address)
        requestJson.put(EMAIL, email)
    }

    @Test
    @Order(1)
    @DisplayName("1. 기본적인 형태의 data class dto binding 테스트")
    fun bindingKtDtoOneCaseTest() {
        logger.info { "basic json data: $requestJson" }

        mvc.perform(
            post("/api/member/kt/one")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson.toString())
        )
            .andExpect(status().isOk)
//            .andExpect(content().string(containsString(createKeyValue(ID, id.toString()))))
            .andExpect(content().string(containsString(createKeyValue(NAME, name))))

    }

    private fun createKeyValue(key: String, value: String?): String {
        var finalValue: String? = "null"
        if (value != null) {
            finalValue = createValueString(key, value)
        }
        return String.format("\"%s\":%s", key, finalValue)
    }

    private fun createValueString(key: String, value: String): String {
        return when (key) {
            ID -> value
            else -> "\"" + value + "\""
        }
    }

}
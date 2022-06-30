package io.beaniejoy.jacksonbindtest.dto.part04

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import io.beaniejoy.jacksonbindtest.common.JsonKeyManager
import io.beaniejoy.jacksonbindtest.dto.part04_kotlin.MemberRequestKtDto1
import io.beaniejoy.jacksonbindtest.dto.part04_kotlin.MemberRequestKtDto2
import mu.KLogging
import org.json.JSONObject
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue

@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
internal class MemberRequestKtDtoTest {
    private lateinit var mapper: ObjectMapper

    private lateinit var json: JSONObject

    private var id: Long = 0L
    private lateinit var name: String
    private lateinit var address: String
    private lateinit var email: String

    companion object: KLogging()

    @BeforeEach
    fun setup() {
//        mapper = ObjectMapper()
        mapper = jacksonObjectMapper()

        id = 100L
        name = "beanie"
        address = "beanie's address"
        email = "beanie's email"

        json = JSONObject()
        json.put(JsonKeyManager.ID, id)
        json.put(JsonKeyManager.NAME, name)
        json.put(JsonKeyManager.ADDRESS, address)
        json.put(JsonKeyManager.EMAIL, email)
    }

    @Test
    @Order(1)
    @DisplayName("1-1. 기본적인 data class 형태 binding 테스트(getter, setter 모두 포함된 data class)")
    fun bindingTestKotlinDataClass() {
        // 1) jackson-module-kotlin 모듈 제공(data class에 기본생성자 제공, default value 지정 안해도 됨)
        // 2) 코틀린에서 제공하는 jacksonObjectMapper()를 사용하면 된다.
//        mapper.registerKotlinModule()

        // 2) 기본생성자 사용하지 않고 data class 지정한 주생성자 사용
//        mapper.registerModule(ParameterNamesModule())

        // 3) data class의 모든 인자에 default value 지정(이거는 상황마다 비효율적일 수 있음)
        val dto = mapper.readValue<MemberRequestKtDto1>(json.toString())

        logger.info { dto }

        assertEquals(dto.id, id)
        assertEquals(dto.name, name)
        assertEquals(dto.address, address)
        assertEquals(dto.email, email)
    }

    @Test
    @Order(2)
    @DisplayName("1-2. 기본적인 data class 형태 직렬화 테스트(getter, setter 모두 포함된 data class)")
    fun serializeTestKotlinDataClass() {
        val dto = MemberRequestKtDto1(
            id = id,
            name = name,
            address = address,
            email = email
        )

        // 직렬화에서는 기본 생성자 없어도 가능(당연)
        val resultJson = mapper.writeValueAsString(dto)
        logger.info { "binding response: ${resultJson}" }
        assertTrue(resultJson.contains("\"id\":100"))
        assertTrue(resultJson.contains("\"name\":\"beanie\""))
        assertTrue(resultJson.contains("\"address\":\"beanie's address\""))
        assertTrue(resultJson.contains("\"email\":\"beanie's email\""))
    }

    @Test
    @Order(2)
    @DisplayName("2. 기본적인 data class 형태 binding 테스트(getter, setter 모두 없는 field 존재)")
    fun bindingTestKotlinDataClassWithNoGetterSetter() {
        // jackson-module-kotlin 모듈에서 private 지정된 인자에 대해서도 프로퍼티를 제공해주는 것으로 보임
        // @field:JsonProperty(...) 이런 방법도 있지만 jackson-kotlin-module 이 알아서 이를 해결해주는 것으로 보임
        val dto = mapper.readValue<MemberRequestKtDto2>(json.toString())

        logger.info { dto }

        assertEquals(dto.id, id)
        assertEquals(dto.name, name)
    }
}
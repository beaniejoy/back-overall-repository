package io.beaniejoy.jacksonbindtest.dto.chap01;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import io.beaniejoy.jacksonbindtest.dto.chap01_basic.MemberRequestDto8;
import io.beaniejoy.jacksonbindtest.dto.chap01_basic.MemberRequestDto9;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.beaniejoy.jacksonbindtest.common.JsonKeyManager.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

// 생성자 테스트
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MemberRequestDtoTest3 {
    private static final Logger logger = LoggerFactory.getLogger(MemberRequestDtoTest3.class);

    Long id;
    String name;
    String address;
    String email;

    ObjectMapper mapper;

    JSONObject json;

    @BeforeEach
    void setup() throws JSONException {
        mapper = new ObjectMapper();

        id = 100L;
        name = "beanie";
        address = "beanie's address";
        email = "beanie's email";

        json = new JSONObject();
        json.put(ID, id);
        json.put(NAME, name);
        json.put(ADDRESS, address);
        json.put(EMAIL, email);
    }

    @Test
    @Order(8)
    @DisplayName("8. POJO 형태에서 기본 생성자만 없는 경우에서의 에러 발생 ObjectMapper 테스트")
    void checkValidMappingWithNoDefaultConstructor() throws JsonProcessingException {
        // Cannot construct instance
        InvalidDefinitionException exception = assertThrows(InvalidDefinitionException.class, () -> {
            MemberRequestDto8 dto = mapper.readValue(json.toString(), MemberRequestDto8.class);
        });
        logger.info(exception.getMessage());
    }

    @Test
    @Order(9)
    @DisplayName("8-1. POJO 형태에서 기본 생성자만 없는 경우에서의 ObjectMapper 역직렬화 테스트")
    void checkValidMappingWithNoDefaultConstructorByParameterNamesModule() throws JsonProcessingException {
        // ParameterNamesModule: 기본생성자, setter 가 없어도 다른 인자가 있는 생성자를 통해 binding 되도록 위임
        mapper.registerModule(new ParameterNamesModule());
        MemberRequestDto8 dto = mapper.readValue(json.toString(), MemberRequestDto8.class);
        logger.info(dto.toString());
    }

    @Test
    @Order(10)
    @DisplayName("8-2. POJO 형태에서 기본 생성자만 없는 경우에서의 ObjectMapper 직렬화 테스트")
    void checkSerializeWithNoDefaultConstructor() throws JsonProcessingException {
        MemberRequestDto8 dto = new MemberRequestDto8(1L, "beanie", "beanie's address", "beanie@example.com");
        String resultJson = mapper.writeValueAsString(dto);
        logger.info(resultJson);

        assertTrue(resultJson.contains("\"id\":1,\"name\":\"beanie\""));
    }

    @Test
    @Order(11)
    @DisplayName("9. Single Argument Constructor인 상황에서의 ObjectMapper 역직렬화 테스트")
    void checkSerializeWithSingleArgumentConstructor() throws JsonProcessingException {
        json.remove("id");
        json.remove("address");
        json.remove("email");
        // 직접 생성자에 @JsonCreator(mode = JsonCreator.Mode.PROPERTIES), @JsonProperty 로 지정할 수도 있다.
        mapper.registerModule(new ParameterNamesModule(JsonCreator.Mode.PROPERTIES));
        assertTrue(String.valueOf(mapper.getRegisteredModuleIds()).contains("jackson-module-parameter-names"));

        MemberRequestDto9 dto = mapper.readValue(json.toString(), MemberRequestDto9.class);
        logger.info(dto.toString());
    }
}

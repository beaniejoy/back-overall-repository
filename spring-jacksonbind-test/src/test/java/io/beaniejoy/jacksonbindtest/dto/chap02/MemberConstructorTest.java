package io.beaniejoy.jacksonbindtest.dto.chap02;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import io.beaniejoy.jacksonbindtest.dto.chap02_constructor.*;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.beaniejoy.jacksonbindtest.common.JsonKeyManager.*;
import static org.junit.jupiter.api.Assertions.*;

// 생성자 테스트
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MemberConstructorTest {
    private static final Logger logger = LoggerFactory.getLogger(MemberConstructorTest.class);

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
    @DisplayName("8. JavaBean 형태에서 기본 생성자만 없는 경우에서의 에러 발생 ObjectMapper 테스트")
    void checkValidMappingWithNoDefaultConstructor() throws JsonProcessingException {
        // Cannot construct instance
        InvalidDefinitionException exception = assertThrows(InvalidDefinitionException.class, () -> {
            MemberConstructorDto1 dto = mapper.readValue(json.toString(), MemberConstructorDto1.class);
        });
        logger.info(exception.getMessage());
    }

    @Test
    @Order(9)
    @DisplayName("8-1. JavaBean 형태에서 기본 생성자만 없는 경우에서의 ObjectMapper 역직렬화 테스트")
    void checkValidMappingWithNoDefaultConstructorByParameterNamesModule() throws JsonProcessingException {
        // ParameterNamesModule: 기본생성자, setter 가 없어도 다른 인자가 있는 생성자를 통해 binding 되도록 위임
        mapper.registerModule(new ParameterNamesModule());
        MemberConstructorDto1 dto = mapper.readValue(json.toString(), MemberConstructorDto1.class);
        logger.info(dto.toString());
        assertEquals(dto.getName(), "beanie");
        assertEquals(dto.getAddress(), "beanie's address");
    }

    @Test
    @Order(10)
    @DisplayName("8-2. POJO 형태에서 기본 생성자만 없는 경우에서의 ObjectMapper 직렬화 테스트")
    void checkSerializeWithNoDefaultConstructor() throws JsonProcessingException {
        MemberConstructorDto1 dto = new MemberConstructorDto1(1L, "beanie", "beanie's address", "beanie@example.com");
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

        MemberConstructorDto2 dto = mapper.readValue(json.toString(), MemberConstructorDto2.class);
        logger.info(dto.toString());
    }

    @Test
    @Order(12)
    @DisplayName("10. 생성자에 @JsonCreator, @JsonProperty를 사용해서 json mapping을 할 수 있다.")
    void checkDeserializeWithJsonCreatorAndProperty() throws JsonProcessingException {
        json.remove("id");
        json.remove("address");
        json.remove("email");
        MemberConstructorDto3 dto = mapper.readValue(json.toString(), MemberConstructorDto3.class);
        logger.info(dto.toString());
        assertEquals(dto.getName(), "beanie");
    }

    @Test
    @Order(13)
    @DisplayName("11. 인자가 두 개 이상인 생성자를 가진 객체로 역직렬화하는 상황 테스트")
    void checkDeserializeWithMoreThanTwoArgumentsConstructor() throws JsonProcessingException {
        json.remove("id");
        json.remove("email");

        // 인자가 두 개 이상인 생성자는 jackson-module-parameter-names 모듈만 등록해줘도 역직렬화 잘 수행해준다.
        // Spring Boot에서 Request json 데이터를 받아올 때 이러한 방식으로 받아오게 되는데 그래서 기본 생성자가 없어도
        mapper.registerModule(new ParameterNamesModule());
        MemberConstructorDto4 dto = mapper.readValue(json.toString(), MemberConstructorDto4.class);
        logger.info(dto.toString());
    }

    @Test
    @Order(14)
    @DisplayName("12. 인자가 있는 생성자와 setter로 역직렬화하는 테스트")
    void checkDeserializeWithConstructorAndSetter() throws JsonProcessingException {
        json.remove("id");

        // constructor에 지정되지 않는 필드(email)가 있으면 setter에 매핑되는 내용을 찾는다.
        mapper.registerModule(new ParameterNamesModule());
        MemberConstructorDto5 dto = mapper.readValue(json.toString(), MemberConstructorDto5.class);
        logger.info(dto.toString());
    }
}

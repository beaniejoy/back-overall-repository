package io.beaniejoy.jacksonbindtest.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.beaniejoy.jacksonbindtest.common.JsonKeyManager.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MemberRequestDtoTest2 {
    private static final Logger logger = LoggerFactory.getLogger(MemberRequestDtoTest2.class);

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
    @Order(5)
    @DisplayName("5. private field & setter 조합만으로 ObjectMapper 테스트")
    void checkValidMappingWithPrivateFieldAndSetter() throws JsonProcessingException {
        MemberRequestDto5 dto = mapper.readValue(json.toString(), MemberRequestDto5.class);

        String dtoInfo = dto.toString();
        logger.info(dtoInfo);

        assertTrue(dtoInfo.contains("id=" + id));
        assertTrue(dtoInfo.contains("name='" + name + "'"));
        assertTrue(dtoInfo.contains("address='" + address + "'"));
        assertTrue(dtoInfo.contains("email='" + email + "'"));

        // disable 설정이 없으면 에러 발생
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        String resultJson = mapper.writeValueAsString(dto);
        logger.info(resultJson);

        assertEquals(resultJson, "{}");
    }

    @Test
    @Order(6)
    @DisplayName("6. 기본적인 POJO 구조에서 setter 에 임의의 값 주입하는 경우에서 ObjectMapper 테스트")
    void checkValidMappingWithSetterCustomValue() throws JsonProcessingException {
        MemberRequestDto6 dto = mapper.readValue(json.toString(), MemberRequestDto6.class);

        logger.info(dto.toString());

        assertEquals(id, dto.getId());
        assertEquals("default name", dto.getName());
        assertEquals("default address", dto.getAddress());
        assertEquals(email, dto.getEmail());
    }

    @Test
    @Order(7)
    @DisplayName("7. setter의 이름이 필드명과 일치하지 않는 경우에서 ObjectMapper 테스트")
    void checkValidMappingWithCustomSetterName() throws JsonProcessingException, JSONException {
        // name, address key 내용 제거
        json.remove(NAME);
        json.remove(ADDRESS);
        // { ... , "helloName": "joy", "helloAddress": "joy's address", ... }
        json.put(HELLO_NAME, "joy");
        json.put(HELLO_ADDRESS, "joy's address");

        MemberRequestDto7 dto = mapper.readValue(json.toString(), MemberRequestDto7.class);

        logger.info(dto.toString());

        assertEquals(id, dto.getId());
        assertEquals("joy", dto.getName());
        assertEquals("joy's address", dto.getAddress());
        assertEquals(email, dto.getEmail());
    }
}

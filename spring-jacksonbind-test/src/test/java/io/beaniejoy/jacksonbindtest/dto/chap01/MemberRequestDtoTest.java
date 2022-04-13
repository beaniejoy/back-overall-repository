package io.beaniejoy.jacksonbindtest.dto.chap01;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.beaniejoy.jacksonbindtest.dto.chap01_basic.MemberRequestDto1;
import io.beaniejoy.jacksonbindtest.dto.chap01_basic.MemberRequestDto2;
import io.beaniejoy.jacksonbindtest.dto.chap01_basic.MemberRequestDto3;
import io.beaniejoy.jacksonbindtest.dto.chap01_basic.MemberRequestDto4;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.beaniejoy.jacksonbindtest.common.JsonKeyManager.*;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MemberRequestDtoTest {
    private static final Logger logger = LoggerFactory.getLogger(MemberRequestDtoTest.class);

    Long id;
    String name;
    String address;
    String email;

    ObjectMapper mapper;

    JSONObject json;

    @BeforeEach
    void setup() throws JSONException {
        mapper = new ObjectMapper();
//        mapper.registerModule(new JavaTimeModule());    // LocalDate

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
    @Order(1)
    @DisplayName("1. public field 만으로 ObjectMapper 테스트")
    void checkValidMappingWithOnlyPublicField() throws JsonProcessingException {
        MemberRequestDto1 dto = mapper.readValue(json.toString(), MemberRequestDto1.class);

        logger.info(dto.toString());
        assertEquals(id, dto.id);
        assertEquals(name, dto.name);
        assertEquals(address, dto.address);
        assertEquals(email, dto.email);
    }

    @Test
    @Order(2)
    @DisplayName("2. private field & getter 만으로 ObjectMapper 테스트")
    void checkValidMappingWithPrivateFieldAndGetterMethod() throws JsonProcessingException {
        MemberRequestDto2 dto = mapper.readValue(json.toString(), MemberRequestDto2.class);

        assertEquals(id, dto.getId());
        assertEquals(name, dto.getName());
        assertEquals(address, dto.getAddress());
        assertEquals(email, dto.getEmail());
    }

    @Test
    @Order(3)
    @DisplayName("3. getter 이름과 field 이름 다른 경우에서 ObjectMapper 예외 발생 테스트")
    void checkValidMappingWithAnotherGetterName() throws JSONException {
        String helloName = "joy";
        String helloAddress = "joy's address";

        // 이번 테스트는 helloName, helloAddress에 초점
        // helloName, helloAddress field만 없고 getter만 존재하는 상황
        json.remove(NAME);                      // "name" 포함시 Unrecognized field "name" 에러 발생 (getName() 존재 X)
        json.remove(ADDRESS);                   // "address" 포함시 Unrecognized field "name" 에러 발생 (getName() 존재 X)
        json.put(HELLO_NAME, helloName);        // Unrecognized field "helloName"
        json.put(HELLO_ADDRESS, helloAddress);  // Unrecognized field "helloAddress"

        JsonProcessingException exception = assertThrows(JsonProcessingException.class, () -> {
            MemberRequestDto3 dto = mapper.readValue(json.toString(), MemberRequestDto3.class);
        }, "JSON parsing 관련 에러가 발생하지 않았습니다.");


        logger.info(exception.getMessage());
    }

    @Test
    @Order(4)
    @DisplayName("4. getter 에서 임의로 지정한 내용을 return 하는 경우")
    void checkValidMappingWithGetterCustomReturnValue() throws JsonProcessingException, JSONException {
        MemberRequestDto4 dto = mapper.readValue(json.toString(), MemberRequestDto4.class);
        // 실제 dto 내부 필드에는 json 내용대로 잘 주입됨
        // api response 내보낼 때 봐야함
        logger.info(dto.toString());

        assertEquals(id, dto.getId());
        assertNotEquals(name, dto.getName());
        assertEquals(address, dto.getAddress());
        assertEquals(email, dto.getEmail());

        String resultJson = mapper.writeValueAsString(dto);
        logger.info(resultJson);

        assertTrue(resultJson.contains("\"name\":\"getName(): Custom return value\""));
    }
}
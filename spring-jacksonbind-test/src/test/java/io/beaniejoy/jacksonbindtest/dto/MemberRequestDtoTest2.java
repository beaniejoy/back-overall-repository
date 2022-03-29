package io.beaniejoy.jacksonbindtest.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.beaniejoy.jacksonbindtest.common.JsonKeyManager.*;
import static io.beaniejoy.jacksonbindtest.common.JsonKeyManager.EMAIL;
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
    @Order(1)
    @DisplayName("5. private field & setter 조합만으로 ObjectMapper 테스트")
    void checkValidMappingWithPrivateFieldAndSetter() throws JsonProcessingException {
        MemberRequestDto5 dto = mapper.readValue(json.toString(), MemberRequestDto5.class);

        String dtoInfo = dto.toString();
        logger.info(dtoInfo)
        ;
        assertTrue(dtoInfo.contains("id=" + id));
        assertTrue(dtoInfo.contains("name='" + name + "'"));
        assertTrue(dtoInfo.contains("address='" + address + "'"));
        assertTrue(dtoInfo.contains("email='" + email + "'"));
    }
}

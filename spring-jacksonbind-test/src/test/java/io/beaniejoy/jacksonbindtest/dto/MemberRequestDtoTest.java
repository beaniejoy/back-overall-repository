package io.beaniejoy.jacksonbindtest.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MemberRequestDtoTest {
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String ADDRESS = "address";
    private static final String EMAIL = "email";

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
}
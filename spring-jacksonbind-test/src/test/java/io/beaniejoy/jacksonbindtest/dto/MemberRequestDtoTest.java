package io.beaniejoy.jacksonbindtest.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MemberRequestDtoTest {
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String ADDRESS = "address";
    private static final String EMAIL = "email";

    ObjectMapper mapper;

    @BeforeEach
    void setup() {
        mapper = new ObjectMapper();
//        mapper.registerModule(new JavaTimeModule());    // LocalDate
    }

    @Test
    @DisplayName("1. public field 만으로 ObjectMapper 테스트")
    void checkValidMappingWithOnlyPublicField() throws JsonProcessingException, JSONException {
        JSONObject json = new JSONObject();

        Long id = 100L;
        String name = "beanie";
        String address = "beanie's address";
        String email = "beanie's email";

        json.put(ID, id);
        json.put(NAME, name);
        json.put(ADDRESS, address);
        json.put(EMAIL, email);

        MemberRequestDto1 dto = mapper.readValue(json.toString(), MemberRequestDto1.class);

        assertEquals(id, dto.id);
        assertEquals(name, dto.name);
        assertEquals(address, dto.address);
        assertEquals(email, dto.email);
    }
}
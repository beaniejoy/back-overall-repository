package io.beaniejoy.resttemplatebasic.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
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
        mapper.registerModule(new JavaTimeModule());    // LocalDate
    }

    @Test
    void checkValidMapping() throws JsonProcessingException {
        String requestJson = "{\"id\": 1, " +
//                "\"name\": \"beanie\", " +
                "\"address\": \"beanie address\", " +
                "\"email\": \"beanie@example.com\", " +
                "\"birthDate\": \"2000-10-25\"," +
                "\"createdAt\": \"2022-03-26T13:20:30\", " +
                "\"createdBy\": \"admin\"}";

        MemberRequestDto requestDto = mapper.readValue(requestJson, MemberRequestDto.class);
        System.out.println(requestDto);
//        assertEquals(requestDto.getId(), 1);
        assertEquals(requestDto.getName(), "beanie");
        assertEquals(requestDto.getAddress(), "beanie address");
    }

    private void checkEqualsDto() {

    }
}
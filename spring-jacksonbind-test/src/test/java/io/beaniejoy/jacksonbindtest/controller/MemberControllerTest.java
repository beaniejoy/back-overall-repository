package io.beaniejoy.jacksonbindtest.controller;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MemberControllerTest {

    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String ADDRESS = "address";
    private static final String EMAIL = "email";

    Long id;
    String name;
    String address;
    String email;

    JSONObject requestJson;

    @Autowired
    MockMvc mvc;

    @BeforeEach
    void setup() throws JSONException {
        id = 100L;
        name = "beanie";
        address = "beanie's address";
        email = "beanie's email";

        requestJson = new JSONObject();

        requestJson.put(ID, id);
        requestJson.put(NAME, name);
        requestJson.put(ADDRESS, address);
        requestJson.put(EMAIL, email);
    }

    @Test
    @Order(1)
    @DisplayName("1. public field 만으로 Request Binding 테스트")
    public void bindingDtoOneTest() throws Exception {
        mvc.perform(post("/api/member/one")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson.toString()))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(createKeyValueLong(ID, id))))
                .andExpect(content().string(containsString(createKeyValueString(NAME, name))))
                .andExpect(content().string(containsString(createKeyValueString(ADDRESS, address))))
                .andExpect(content().string(containsString(createKeyValueString(EMAIL, email))))
                .andDo(print());
    }

    @Test
    @Order(2)
    @DisplayName("2. private field & getter 만으로 Request Binding 테스트")
    public void bindingDtoTwoTest() throws Exception {
        mvc.perform(post("/api/member/two")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson.toString()))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(createKeyValueLong(ID, id))))
                .andExpect(content().string(containsString(createKeyValueString(NAME, name))))
                .andExpect(content().string(containsString(createKeyValueString(ADDRESS, address))))
                .andExpect(content().string(containsString(createKeyValueString(EMAIL, email))))
                .andDo(print());
    }

    private String createKeyValueLong(String key, Long value) {
        return String.format("\"%s\":%d", key, value);
    }

    private String createKeyValueString(String key, String value) {
        return String.format("\"%s\":\"%s\"", key, value);
    }
}
package io.beaniejoy.jacksonbindtest.controller;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static io.beaniejoy.jacksonbindtest.common.JsonKeyManager.*;
import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MemberControllerTest {
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
                .andExpect(content().string(containsString(createKeyValue(ID, id.toString()))))
                .andExpect(content().string(containsString(createKeyValue(NAME, name))))
                .andExpect(content().string(containsString(createKeyValue(ADDRESS, address))))
                .andExpect(content().string(containsString(createKeyValue(EMAIL, email))))
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
                .andExpect(content().string(containsString(createKeyValue(ID, id.toString()))))
                .andExpect(content().string(containsString(createKeyValue(NAME, name))))
                .andExpect(content().string(containsString(createKeyValue(ADDRESS, address))))
                .andExpect(content().string(containsString(createKeyValue(EMAIL, email))))
                .andDo(print());
    }

    @Test
    @Order(3)
    @DisplayName("3. getter 이름과 field 이름 다른 경우에서 null 반환 테스트")
    public void bindingDtoThreeTest() throws Exception {
        String helloName = "joy";
        String helloAddress = "joy's address";

        requestJson.put(HELLO_NAME, helloName);
        requestJson.put(HELLO_ADDRESS, helloAddress);

        // 기존의 name, address는 매칭되는 getter 가 없어서 response json에 아예 포함 X
        mvc.perform(post("/api/member/three")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson.toString()))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(createKeyValue(ID, id.toString()))))
                .andExpect(content().string(containsString(createKeyValue(HELLO_NAME, null))))
                .andExpect(content().string(containsString(createKeyValue(HELLO_ADDRESS, null))))
                .andExpect(content().string(containsString(createKeyValue(EMAIL, email))))
                .andDo(print());
    }

    private String createKeyValue(String key, String value) {
        String finalValue = "null";

        if (value != null) {
            finalValue = createValueString(key, value);
        }

        return String.format("\"%s\":%s", key, finalValue);
    }

    private String createValueString(String key, String value) {
        switch (key) {
            case ID:
                return value;
            default:
                return "\"" + value + "\"";
        }
    }
}
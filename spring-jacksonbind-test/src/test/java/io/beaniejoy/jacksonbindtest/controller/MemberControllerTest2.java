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
class MemberControllerTest2 {
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
    @DisplayName("5. private field & setter 조합만으로 406 응답코드 반환 테스트")
    public void bindingDtoOneCaseTest() throws Exception {
        // getter가 없어서 response를 내보낼 때 각 field에 저장된 데이터에 접근할 수 없게됨

        mvc.perform(post("/api/member/five")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson.toString()))
                .andExpect(status().isNotAcceptable())
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
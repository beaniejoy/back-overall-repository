package io.beaniejoy.jacksonbindtest.controller;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.test.web.servlet.MockMvc;

import static io.beaniejoy.jacksonbindtest.common.JsonKeyManager.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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
    @Order(5)
    @DisplayName("5. private field & setter 조합만으로 406 응답코드(Not Acceptable) 반환 테스트")
    public void bindingDtoFiveCaseTest() throws Exception {
        // getter가 없어서 response를 내보낼 때 각 field에 저장된 데이터에 접근할 수 없게됨
        mvc.perform(post("/api/member/five")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson.toString()))
                .andExpect(status().isNotAcceptable())
                .andDo(print());
    }

    /**
     * 참고로
     * 406 에러는 헤더에 지정한 Accept 같은 형식을 생성해낼 수 없을 때 (HttpMediaTypeNotAcceptableException)
     *   - 객체로 바인딩은 됐지만 return 할 때 원하는 형태로 변환이 이루어지지 않아 발생하는 에러
     * 400 에러는 여기서 @RequestBody에 지정한 객체로 역직렬화가 되지 않아 실패(HttpMessageNotReadableException)
     */
    @Test
    @Order(8)
    @DisplayName("8. POJO 형태에서 기본 생성자만 없는 경우 테스트")
    public void bindingDtoEightCaseTest() throws Exception {
        // 기본 생성자가 없어도 @RequestBody 에서는 값을 제대로 binding 해준다.
        // 기본 생성자 > 해당 필드를 넣을 수 있는 인자 있는 생성자 > setter 적용
        mvc.perform(post("/api/member/eight")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson.toString()))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @Order(9)
    @DisplayName("9. 인자가 한 개인 생성자만 존재하는 경우 테스트")
    public void bindingDtoNineCaseTest() throws Exception {
        requestJson.remove(ID);
        requestJson.remove(ADDRESS);
        requestJson.remove(EMAIL);

        // @JsonCreator 가 없는 경우
        // 400 에러는 여기서 @RequestBody에 지정한 객체로 역직렬화가 되지 않아 실패(HttpMessageNotReadableException)
        // cannot deserialize from Object value (no delegate- or property-based Creator)
        mvc.perform(post("/api/member/nine")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson.toString()))
                .andExpect(status().isOk())
//                .andExpect(result ->
//                        assertTrue(result.getResolvedException() instanceof HttpMessageNotReadableException)
//                )
                .andDo(print());
    }

    @Test
    @Order(10)
    @DisplayName("12. 인자가 있는 생성자와 setter로 request binding 테스트")
    public void bindingDtoTwelveCaseTest() throws Exception {
        requestJson.remove(ID);

        mvc.perform(post("/api/member/twelve")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson.toString()))
                .andExpect(status().isOk())
                .andDo(print());
    }
}
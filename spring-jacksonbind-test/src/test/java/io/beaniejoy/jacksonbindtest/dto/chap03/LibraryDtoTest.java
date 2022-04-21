package io.beaniejoy.jacksonbindtest.dto.chap03;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.beaniejoy.jacksonbindtest.dto.chap03_collection.LibraryDto;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

// 생성자 테스트
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LibraryDtoTest {
    private static final Logger logger = LoggerFactory.getLogger(LibraryDtoTest.class);

    ObjectMapper mapper;

    JSONObject json;
    JSONArray jsonArr;

    @BeforeEach
    void setup() {
        mapper = new ObjectMapper();

        json = new JSONObject();
        jsonArr = new JSONArray();
    }

    @Test
    @Order(1)
    @DisplayName("chap02-1-1 List 필드에 대한 직렬화, 역직렬화 테스트")
    void checkListTypeFieldBingingTest() throws JsonProcessingException, JSONException {
        json.put("name", "beanie's Library");
        jsonArr.put("book1");
        jsonArr.put("book2");
        json.put("books", jsonArr);

        LibraryDto dto = mapper.readValue(json.toString(), LibraryDto.class);
        assertEquals(dto.getBooks().size(), 2);
        assertEquals(dto.getBooks().get(0), "book1");

        String result = mapper.writeValueAsString(dto);
        assertEquals(result, "{\"name\":\"beanie's Library\",\"books\":[\"book1\",\"book2\"]}");
    }

    @Test
    @Order(2)
    @DisplayName("chap02-1-2 List 필드에 대한 null 처리 테스트")
    void checkListTypeFieldWithNullPointer() throws JsonProcessingException, JSONException {
        json.put("name", "beanie's Library");
        json.put("books", null);

        LibraryDto dto = mapper.readValue(json.toString(), LibraryDto.class);
        assertNull(dto.getBooks());

        String result = mapper.writeValueAsString(dto);
        assertEquals(result, "{\"name\":\"beanie's Library\",\"books\":null}");
    }
}

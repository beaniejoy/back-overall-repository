package io.beaniejoy.junittest;

import org.junit.jupiter.api.*;

//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class StudyInstanceTest {
    @Test
//    @DisplayName("스터디 만들기 1")
    @Disabled
    void create_new_study_fast() {
        System.out.println(this);   // 인스턴스가 같다.
        System.out.println("create study 1");
    }

    @Test
//    @DisplayName("스터디 만들기 2")
    void create_new_study_slow() {
        System.out.println(this);   // 인스턴스가 같다.
        System.out.println("create study 2");
    }

    // static 필요 X
    @BeforeAll
    void beforeAll() {
        System.out.println("before all");
    }

    // static 필요 X
    @AfterAll
    void afterAll() {
        System.out.println("after all");
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("before each");
    }

    @AfterEach
    void afterEach() {
        System.out.println("after each");
    }
}
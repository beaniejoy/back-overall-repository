package io.beaniejoy.junittest;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumingThat;

//@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class StudyTest {
    @Test
    @DisplayName("스터디 만들기")
    void create_new_study() {
        IllegalArgumentException exception
                = assertThrows(IllegalArgumentException.class, () -> new Study(-10));
        assertEquals("limit은 0보다 커야 합니다.", exception.getMessage());

        // 람다 안에 내용을 다 실행하고 나서 비교
//        assertTimeout(Duration.ofMillis(100), () -> {
//            new Study(10);
//            Thread.sleep(300);
//        });

        // 리미트 걸어둔 시간까지만 측정
//        assertTimeoutPreemptively(Duration.ofMillis(100), () -> {
//            new Study(10);
//            Thread.sleep(300);
//        });

//        assertAll(
//                () -> assertNotNull(study),
//                () -> assertEquals(StudyStatus.DRAFT, study.getStatus(), "스터디 최초 상태는 DRAFT여야 합니다."),
//                () -> assertTrue(study.getLimit() > 0, "스터디 최대 참석 가능 인원은 0보다 커야 합니다.")
//        );
    }

    @Test
    @DisplayName("스터디 만들기 2")
    @EnabledIfEnvironmentVariable(named = "TEST_ENV", matches = "LOCAL")
    void create_new_study_2() {
        String test_env = System.getenv("TEST_ENV");
        System.out.println(test_env);
//        assumeTrue("LOCAL".equalsIgnoreCase(test_env)); // local 인 경우에만 아래 테스트 실행

        assumingThat("LOCAL".equalsIgnoreCase(test_env), () -> {
            System.out.println("LOCAL TEST");
        });

        assumingThat("BEANIE".equalsIgnoreCase(test_env), () -> {
            System.out.println("BEANIE TEST");
        });

        Study study = new Study(10);
        assertTrue(study.getLimit() > 0);
    }

    @Test
    @DisabledOnOs(OS.MAC)
    void create_new_study_again() {
        System.out.println("create1");
    }

    // Tag
    @FastTest
    @DisplayName("스터디 만들기 fast")
    void create_new_study_fast() {
        System.out.println("create fast");
    }

    @SlowTest
    @DisplayName("스터디 만들기 slow")
    void create_new_study_slow() {
        System.out.println("create slow");
    }

    @DisplayName("스터디 만들기 RepeatedTest")
    @RepeatedTest(value = 10, name = "{displayName}, {currentRepetition}/{totalRepetitions}")
    void repeatTest(RepetitionInfo repetitionInfo) {
        // 현재 몇번째 반복 진행중인지 / 전체 반복 횟수
        System.out.println("test " + repetitionInfo.getCurrentRepetition() + "/" + repetitionInfo.getTotalRepetitions());
    }

    // JUnit5 기본 제공
    @DisplayName("스터디 만들기 ParameterizedTest")
    @ParameterizedTest(name = "{index} {displayName} message={0}")
    @ValueSource(strings = {"날씨가", "많이", "추워지고", "있네요."})
    void parameterizedTest(String message) {
        System.out.println(message);
    }


    // private X, static void만 가능
    @BeforeAll
    static void beforeAll() {
        System.out.println("before all");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("after all");
    }

    // static일 필요 없음
    @BeforeEach
    void beforeEach() {
        System.out.println("before each");
    }

    @AfterEach
    void afterEach() {
        System.out.println("after each");
    }
}
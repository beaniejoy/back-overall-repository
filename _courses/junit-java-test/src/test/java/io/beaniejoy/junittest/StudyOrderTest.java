package io.beaniejoy.junittest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StudyOrderTest {
    @Order(2)
    @DisplayName("스터디 만들기 1")
    void create_new_study_1() {
        System.out.println("create test 1");
    }

    @Order(1)
    @DisplayName("스터디 만들기 2")
    void create_new_study_2() {
        System.out.println("create test 2");
    }
}

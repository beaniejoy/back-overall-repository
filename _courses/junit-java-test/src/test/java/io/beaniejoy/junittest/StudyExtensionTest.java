package io.beaniejoy.junittest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;

/**
 * JUnit5 extension 등록 방법
 */
//@ExtendWith(FindSlowExtension.class) // 1. junit extension 선언적으로 등록하는 방법
class StudyExtensionTest {
    // constructor를 통해서 THRESHOLD 값을 조절하고 싶을 때 @RegisterExtension 사용
    @RegisterExtension
    static FindSlowExtension findSlowExtension = new FindSlowExtension(1000L);


    @FastTest
    @DisplayName("스터디 만들기 fast")
    void create_new_study_fast() {
        System.out.println("create fast");
    }

    @SlowTest
//    @Test
    @DisplayName("스터디 만들기 slow")
    void create_new_study_slow() throws InterruptedException {
        Thread.sleep(1005L);
        System.out.println("create slow");
    }
}

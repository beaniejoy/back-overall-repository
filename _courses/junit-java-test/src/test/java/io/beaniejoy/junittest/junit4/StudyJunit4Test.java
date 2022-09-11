package io.beaniejoy.junittest.junit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * JUnit4 - public class, public method
 */
public class StudyJunit4Test {
    @Before
    public void before() {
        System.out.println("before");
    }

    @After
    public void after() {
        System.out.println("after");
    }

    @Test
    public void createTest() {
        System.out.println("test");
    }
}

package io.beaniejoy.springdatajpa.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CafeRepositoryTest {

    @Autowired
    CafeRepository cafeRepository;

    @Test
    @DisplayName("Specification을 통한 동적쿼리 Repo Test")
    void findAllWithSpecification() {

    }
}

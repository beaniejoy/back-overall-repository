package io.beaniejoy.springdatajpa.querydsl;

import com.querydsl.jpa.impl.JPAQuery;
import io.beaniejoy.springdatajpa.entity.cafe.Cafe;
import io.beaniejoy.springdatajpa.entity.cafe.QCafe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CafeRepositoryQuerydslTest {
//    @PersistenceUnit
//    private EntityManagerFactory emf;
    @Autowired
    TestEntityManager testEntityManager;

    EntityManager em;

    @BeforeEach
    void init() {
        em = testEntityManager.getEntityManager();
    }

    @Test
    @DisplayName("cafe name 기준으로 equal 조회")
    void findByNameTest() {
        JPAQuery<Cafe> query = new JPAQuery<>(em);
        QCafe qCafe = new QCafe("c");

        List<Cafe> cafeList = query.from(qCafe)
                .where(qCafe.name.eq("test_cafe_1"))
                .fetch();

        assertEquals(cafeList.size(), 1);
    }

    @Test
    @DisplayName("cafe address 기준으로 like, id 기준 desc 조회")
    void findByAddressContainingOrderByIdDescTest() {
        JPAQuery<Cafe> query = new JPAQuery<>(em);
        QCafe qCafe = new QCafe("c");

        List<Cafe> cafeList = query.from(qCafe)
                .where(qCafe.address.contains("address"))
                .orderBy(qCafe.id.desc())
                .fetch();

        assertEquals(cafeList.size(), 3);
        assertEquals(cafeList.get(0).getName(), "test_cafe_3");
    }
}

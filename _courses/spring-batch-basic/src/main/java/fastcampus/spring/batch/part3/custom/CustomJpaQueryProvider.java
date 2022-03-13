package fastcampus.spring.batch.part3.custom;

import fastcampus.spring.batch.source.Person;
import org.springframework.batch.item.database.orm.AbstractJpaQueryProvider;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

public class CustomJpaQueryProvider extends AbstractJpaQueryProvider {

    @Override
    public Query createQuery() {
        EntityManager em = getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Person> cq = cb.createQuery(Person.class);

        Root<Person> root = cq.from(Person.class);

        Order idDesc = cb.desc(root.get("id"));

        cq.select(root)
                .orderBy(idDesc);

        return em.createQuery(cq);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
    }
}

package fastcampus.spring.batch.source;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity // Entity 설정시 이를 기반으로 table 을 생성하기에 DDL 을 주석처리해준다.
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private Integer age;

    private String address;

    public Person(String name, Integer age, String address) {
        // id의 default value인 0으로 설정하면 JPA가 GeneratedValue에 따라 알아서 Id 생성
        this(0, name, age, address);
    }

    public Person(Integer id, String name, Integer age, String address) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
    }
}

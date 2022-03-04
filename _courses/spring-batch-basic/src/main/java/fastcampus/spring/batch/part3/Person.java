package fastcampus.spring.batch.part3;

import lombok.Getter;

@Getter
public class Person {
    private Integer id;
    private String name;
    private Integer age;
    private String address;

    public Person(Integer id, String name, Integer age, String address) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
    }
}

package chap9.generics;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class JuiceDemo {
    public static void main(String[] args) {
        // 두 개의 FruitBox 제너릭 클래스는 다른 타입이다. (convert 불가능)
        FruitBox<Fruit> fruitBox = new FruitBox<>();
        FruitBox<Apple> appleBox = new FruitBox<>();

        System.out.println(Juicer.makeJuice(fruitBox));
        // makeJuice(FruitBox<? extends Fruit> box) <- 와일드카드로 확장할 수 있다.
        System.out.println(Juicer.makeJuice(appleBox)); // 와일드카드가 아닌 <Fruit>면 에러발생

        SaltClass<Student> a = new SaltClass<Student>();

        String s = "hello world";
        String[] s1 = s.split(" ");
        List<String> list = Arrays.stream(s1).filter(str -> !str.trim().isEmpty()).collect(Collectors.toList());
        Collections.reverse(list);
        String.join(" ", list);
    }

    static class SaltClass <E extends Comparable<? super E>> {

    }
    class Student extends Person implements Comparable<Person> {
        @Override
        public int compareTo(@NotNull Person o) {
            return 0;
        }
    }

    class Person {

    }

}

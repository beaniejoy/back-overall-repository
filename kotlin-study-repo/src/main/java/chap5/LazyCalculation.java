package chap5;

import chap2.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LazyCalculation {
    public static void main(String[] args) {

        Person[] personArr = {
                new Person("p1", 20),
                new Person("p2", 25)
        };

        List<Person> persons = new ArrayList<>(Arrays.asList(personArr));
        List<String> filteredPersons = persons.stream()
                .map(Person::getName)
                .filter(s -> s.startsWith("p"))
                .collect(Collectors.toList());
    }
}

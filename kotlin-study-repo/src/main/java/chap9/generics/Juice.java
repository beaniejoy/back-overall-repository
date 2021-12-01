package chap9.generics;

public class Juice {
    private String name;

    public Juice(String name) {
        this.name = name + "Juice";
    }

    public String toString() {
        return name;
    }
}

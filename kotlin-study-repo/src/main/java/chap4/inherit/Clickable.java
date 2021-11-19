package chap4.inherit;

public interface Clickable {
    void click();

    default void showOff() {
        System.out.println("Clickable!!");
    }
}

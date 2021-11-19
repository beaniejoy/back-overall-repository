package chap4.inherit;

public interface Focusable {
    default void setFocus(boolean b) {
        String focused = b ? "got" : "lost";
        System.out.println("I " + focused + " focus.");
    }

    default void showOff() {
        System.out.println("Focusable!!");
    }
}

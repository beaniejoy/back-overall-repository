package chap4.inherit;

public class Button implements Clickable, Focusable{
    @Override
    public void click() {
        System.out.println("Button Clickable!!");
    }

    @Override
    public void showOff() {
        Clickable.super.showOff();
    }
}

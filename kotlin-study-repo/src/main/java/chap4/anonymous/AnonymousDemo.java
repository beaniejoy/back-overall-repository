package chap4.anonymous;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AnonymousDemo {
    public static void main(String[] args) {
        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
            }
        };
    }
}

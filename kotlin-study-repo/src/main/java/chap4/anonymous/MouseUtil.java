package chap4.anonymous;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseUtil {
    public void countClick(Window window) {
        int clickCount = 0;
        window.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println(clickCount);
            }
        });
    }
}

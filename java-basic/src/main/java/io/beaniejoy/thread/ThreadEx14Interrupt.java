package io.beaniejoy.thread;

import javax.swing.*;

public class ThreadEx14Interrupt {
    public static void main(String[] args) {
        ThreadEx14_1 th1 = new ThreadEx14_1();
        th1.start();

        String input = JOptionPane.showInputDialog("아무값이나 입력하세요.");
        System.out.println("입력한 값 : " + input);

        th1.interrupt();
        System.out.println("th1 isInterrupted(): " + th1.isInterrupted());
    }

    private static class ThreadEx14_1 extends Thread {
        @Override
        public void run() {
            int i = 10;

            while (i != 0 && !isInterrupted()) {
                System.out.println(i--);
                try {
                    Thread.sleep(1000);
                    // 1. sleep 상태에서 interrupt 되면 InterruptedException 발생
                } catch (InterruptedException e) {
                    // 2. InterruptedException 발생 후 interrupted 상태 false 로 자동 초기화
                    // 그래서 여기서 다시 interrupt()를 해야 함
                    interrupt();
                }
            }

            System.out.println("Count is Over");
        }
    }

}

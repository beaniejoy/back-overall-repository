package io.beaniejoy.thread;

public class ThreadEx19Join {
    static long startTime = 0;

    public static void main(String[] args) {
        ThreadEx19_1 th1 = new ThreadEx19_1();
        ThreadEx19_2 th2 = new ThreadEx19_2();
        th1.start();
        th2.start();
        startTime = System.currentTimeMillis();

        try {
            th1.join();
            th2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // join 이 없으면 main thread 는 0초만에 끝난다.
        System.out.println("time lapse : " + (System.currentTimeMillis() - ThreadEx19Join.startTime));
    }

    private static class ThreadEx19_1 extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 300; i++) {
                System.out.print(new String("-"));
            }
        }
    }

    private static class ThreadEx19_2 extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 300; i++) {
                System.out.print(new String("|"));
            }
        }
    }

}

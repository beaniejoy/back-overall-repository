package io.beaniejoy.thread;

/*
* suspend, resume, stop 은 dead lock 이슈로 인해 deprecated 상태
*/
public class ThreadEx15Suspend {
    public static void main(String[] args) {
        RunnableImpl r = new RunnableImpl();
        Thread th1 = new Thread(r, "thread 1");
        Thread th2 = new Thread(r, "thread 2");
        Thread th3 = new Thread(r, "thread 3");

        th1.start();
        th2.start();
        th3.start();

        try {
            Thread.sleep(2000);
            th1.suspend();
            Thread.sleep(2000);
            th2.suspend();
            Thread.sleep(3000);
            th1.resume();
            Thread.sleep(3000);
            th1.stop();
            th2.stop();
            Thread.sleep(2000);
            th3.stop();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static class RunnableImpl implements Runnable {

        @Override
        public void run() {
            while (true) {
                System.out.println(Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {

                }

            }
        }
    }
}


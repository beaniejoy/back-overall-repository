package io.beaniejoy.thread;

public class ThreadEx20JoinGc {
    public static void main(String[] args) {
        ThreadEx20_1 gc = new ThreadEx20_1();
        gc.setDaemon(true); // main thread가 종료되면 daemon thread도 같이 종료됨
        gc.start();

        int requiredMemory = 0;

        for (int i = 0; i < 20; i++) {
            requiredMemory = (int) ((Math.random() * 10) * 20);

            if (gc.freeMemory() < requiredMemory || gc.freeMemory() < gc.totalMemory() * 0.4) {
                gc.interrupt();
                try {
                    gc.join(100);
                    // 여기서 join 하지 않으면 main thread 에서 계속 아래로 진행할 것임
                    // 그렇게 되면 usedMemory 는 계속해서 커져가기만 하는 상황 발생
                    // usedMemory 가 totalMemory 보다 커지는 상황이 생김
                    // gc가 interrupt 된 이후 join 을 통해 main thread 가 gc 과정이 다 마칠 때까지 기다려야 함
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            gc.usedMemory += requiredMemory;
            System.out.println("usedMemory:" + gc.usedMemory);
        }
    }

    private static class ThreadEx20_1 extends Thread {
        final static int MAX_MEMORY = 1000;
        int usedMemory = 0;

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(10 * 1000);
                    // 10초 동안 기다리다가 gc.interrupt() 된 순간 sleep 상태를 깨우게 됨
                    // (InterruptedException 발생)
                } catch (InterruptedException e) {
                    System.out.println("Awaken GC by interrupt()");
                }

                gc();
                System.out.println("GC. Free Memory :" + freeMemory());
            }
        }

        void gc() {
            usedMemory -= 300;
            if (usedMemory < 0) usedMemory = 0;
        }

        int totalMemory() {
            return MAX_MEMORY;
        }

        int freeMemory() {
            return MAX_MEMORY - usedMemory;
        }
    }

}

package io.beaniejoy.thread;

public class ThreadSyncTest {

    public static void main(String[] args) {
        Account account = new Account(2000);

        ThreadCustomer cust1 = new ThreadCustomer(account);
        ThreadCustomer cust2 = new ThreadCustomer(account);

        new Thread(cust1, "CUST-1").start();    // withdraw
        new Thread(cust2, "CUST-2").start();    // getBalance
    }

    private static class Account {
        private long balance;

        Account(long balance) {
            this.balance = balance;
        }

        synchronized long getBalance(String name) {
            System.out.println(name + " getBalance " + balance);
            return balance;
        }

        synchronized void withdraw(String name, long money) {
            System.out.println(name + " withdraw " + money);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(name + " withdraw end.");
        }
    }

    private static class ThreadCustomer implements Runnable {
        private Account account;

        ThreadCustomer(Account balance) {
            this.account = balance;
        }

        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            if (name.equals("CUST-1"))
                account.withdraw(name, account.balance);
            else
                account.getBalance(name);
        }
    }
}
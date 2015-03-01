package pl.mg.javatst.threads;

import pl.mg.javatst.util.Println;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by m on 2015-03-01.
 */
public class DeadlockTest {

    public static void main(String[] args) {


        final DeadlockThread test = new DeadlockThread();

        Thread th1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    test.firstThread();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });


        Thread th2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    test.secondThread();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        th1.start();
        th2.start();

        try {
            th1.join();
            th2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        test.finished();
    }
}


class DeadlockThread {
    private Account acc1 = new Account();
    private Account acc2 = new Account();

    private Lock lock1 = new ReentrantLock();
    private Lock lock2 = new ReentrantLock();

    private void acquireLocks(Lock firstLock, Lock secondLock) throws InterruptedException {
        while (true) {
            boolean gotFirstLock = false;
            boolean gotSecondLock = false;

            try {
                gotFirstLock = lock1.tryLock();
                gotSecondLock = lock2.tryLock();
            } finally {
                if (gotFirstLock && gotSecondLock) {
                    return;
                }
                if (gotFirstLock) {
                    firstLock.unlock();
                }
                if (gotSecondLock) {
                    secondLock.unlock();
                }
            }
            Thread.sleep(1);
        }
    }

    public void firstThread() throws InterruptedException {

        Random random = new Random();
        for (int i = 0; i < 10000; i++) {
            acquireLocks(lock1, lock2);
            try {
                Account.tranfer(acc1, acc2, random.nextInt(100));
            } finally {
                acquireLocks(lock1, lock2);
            }
        }
    }

    public void secondThread() throws InterruptedException {
        Random random = new Random();
        for (int i = 0; i < 10000; i++) {
            acquireLocks(lock1, lock2);
            try {
                Account.tranfer(acc2, acc1, random.nextInt(100));
            } finally {
                acquireLocks(lock1, lock2);
            }
        }
    }

    public void finished() {
        Println.print("account 1 balance: " + acc1.getBalance());
        Println.print("account 2 balance: " + acc2.getBalance());
    }
}

class Account {
    private int balance = 1000;

    public void deposit(int amount) {
        balance += amount;
    }

    public void withdraw(int amount) {
        balance -= amount;
    }

    public int getBalance() {
        return balance;
    }

    public static void tranfer(Account acc1, Account acc2, int amount) {
        acc1.withdraw(amount);
        acc2.deposit(amount);
    }
}

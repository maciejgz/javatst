package pl.mg.javatst.threads;


import pl.mg.javatst.util.Println;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Testy reetrant lock
 * Created by m on 2015-02-28.
 */
public class ReentrantLockTest {

    private int count = 0;

    private Lock lock = new ReentrantLock();
    private Condition con = lock.newCondition();

    public static void main(String[] args) {
        ReentrantLockTest test = new ReentrantLockTest();
        try {
            test.firstThread();
            test.secondThread();
            test.finished();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void increment() {
        for (int i = 0; i < 10000; i++) {
            count++;
        }
    }

    public void firstThread() throws InterruptedException {
        lock.lock();


        Println.print("waitin...");
//        con.await();

        try {
            increment();
            Thread.sleep(10000);
        } finally {
            lock.unlock();
        }

    }

    public void secondThread() throws InterruptedException {
//        Thread.sleep(1000);
        lock.lock();

        Println.print("Press return key...");
//        new Scanner(System.in).nextLine();
//
//        Println.print("GOT return key...");
//        con.signal();
        try {
            increment();
        } finally {
            lock.unlock();
        }
    }

    public void finished() {
        System.out.println("Count is: " + count);
    }
}

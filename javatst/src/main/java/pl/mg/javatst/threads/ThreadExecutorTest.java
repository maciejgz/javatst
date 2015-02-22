package pl.mg.javatst.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadExecutorTest {

    public static void main(String[] args) {

        ExecutorService executor = Executors.newCachedThreadPool();

        executor.execute(new SleepThread("1"));
        try {
            executor.awaitTermination(6, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(executor.isTerminated());
        executor.execute(new SleepThread("2"));
    }
}

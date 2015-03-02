package pl.mg.javatst.threads;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Testy countdown latch, czyli symulacja kroków do wykonania
 * Created by m on 2015-02-25.
 */
public class CountDownLatchTest {
    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(3);


        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 3; i++) {
            executorService.submit(new ProcessorLatch(latch));
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("completed");
    }

}

class ProcessorLatch implements Runnable {
    private CountDownLatch latch;

    public ProcessorLatch(CountDownLatch latch) {
        this.latch = latch;
    }


    @Override
    public void run() {
        System.out.println("Start");
        //latch.countDown();
    }
}

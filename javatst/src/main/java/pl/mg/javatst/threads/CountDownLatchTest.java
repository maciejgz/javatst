package pl.mg.javatst.threads;

import java.util.concurrent.CountDownLatch;

/**
 * Created by m on 2015-02-25.
 */
public class CountDownLatchTest {
    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(3);
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
        latch.countDown();
    }
}

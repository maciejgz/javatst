package pl.mg.javatst.threads;

import pl.mg.javatst.util.Println;

import java.util.Random;

/**
 * Created by m on 2015-03-01.
 */
public class InterruptingThreadsTest {
    public static void main(String[] args) throws InterruptedException {
        Println.print("start");
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Random random = new Random();
                for (int i = 0; i < 1E8; i++) {
                    Math.sin(random.nextDouble());
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        break;
                    }
                }
            }
        });
        thread.start();

        Thread.sleep(500);
        thread.interrupt();
        thread.join();
        Println.print("finish");
    }
}

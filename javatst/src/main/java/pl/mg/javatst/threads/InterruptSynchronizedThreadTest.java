package pl.mg.javatst.threads;

import java.util.Scanner;

/**
 * Przerywanie wątku ze zmienną volatile.
 * Created by m on 2015-02-22.
 */
public class InterruptSynchronizedThreadTest {


    public static void main(String[] args) {
        Processor processor = new Processor();
        processor.start();
        System.out.println("Press tap...");
        Scanner scanner = new Scanner(System.in);

        scanner.nextLine();

        processor.stopExecution();

    }
}


class Processor extends Thread {

    private volatile boolean interrupt = false;

    @Override
    public void run() {
        while (!interrupt) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(System.nanoTime());
        }
    }

    public void stopExecution() {
        this.interrupt = true;
    }
}

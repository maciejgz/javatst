package pl.mg.javatst.threads;

import java.util.Scanner;

/**
 * Testy bloków synchronized na obiekcie lock na wzorcu producer consumer wykorzystującym ten sam obiket blokujący
 * Created by m on 2015-02-25.
 */
public class ProcessorWaitNotify {
    private Object block1 = new Object();

    public void produce() throws InterruptedException {
        synchronized (block1) {
            System.out.println("Producer thread running...");
            block1.wait();
            System.out.println("resumed...");
        }
    }

    public void consume() throws InterruptedException {
        Thread.sleep(2000);
        Scanner scanner = new Scanner(System.in);
        synchronized (block1) {

            System.out.println("Waiting for return key...");
            scanner.nextLine();
            System.out.println("Return key pressed...");
            block1.notify();
        }
    }


    public static void main(String[] args) throws InterruptedException {
        final ProcessorWaitNotify processorMain = new ProcessorWaitNotify();
        Thread th1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    processorMain.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread th2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    processorMain.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        th1.start();
        th2.start();

        th1.join();
        th2.join();
    }
}

package pl.mg.javatst.threads_video_tut;

import pl.mg.javatst.util.Println;

import java.util.LinkedList;
import java.util.Random;

/**
 * Testy wait i notify
 * Created by m on 2015-02-25.
 */
public class ProcessorSharedDataSync {
    private LinkedList<Integer> list = new LinkedList<>();
    private final int LIMIT = 10;

    private Object block1 = new Object();

    public void produce() throws InterruptedException {
        int value = 0;
        while (true) {
            synchronized (block1) {

                /**
                 * Oczekiwanie na zwolnienie miejsca przez consume
                 */
                while (list.size() == LIMIT) {
                    block1.wait();
                }
                list.add(value++);
                //wybudzanie wątku gdy dodamy (np. pierwszy) obiekt
                block1.notify();
            }
        }
    }

    public void consume() throws InterruptedException {

        Random random = new Random();
        while (true) {
            synchronized (block1) {
                while (list.size() == 0) {
                    block1.wait();
                }
                Println.print("List size is=" + list.size());
                int value = list.removeFirst();
                Println.print("; value is: " + value);
                block1.notify();
            }

            Thread.sleep(random.nextInt(1000));
        }
    }


    public static void main(String[] args) throws InterruptedException {
        final ProcessorSharedDataSync processorMain = new ProcessorSharedDataSync();
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

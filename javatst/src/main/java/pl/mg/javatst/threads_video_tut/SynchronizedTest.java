package pl.mg.javatst.threads_video_tut;

/**
 * Created by m on 2015-02-23.
 */
public class SynchronizedTest {

    private int count = 0;

    public static void main(String[] args) {

        SynchronizedTest sync = new SynchronizedTest();

        sync.doWork();

    }

    /**
     * Synchronizacja tej metody pozwala na synnchronizację dostępu do
     * zmiennej i blokowanie zmian w trakcie gdy aktualizuje ją inny wątek.
     */
    private synchronized void add() {
        this.count++;
    }


    private void doWork() {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    add();
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    SynchronizedTest.this.add();
                }
            }
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(this.count);
    }
}

package pl.mg.javatst.threads;

public class SynchronizationTest {

    public static void main(String[] args) {

        SynchronizedObject syncing = new SynchronizedObject();
        Thread th1 = new Thread(new SyncThread(syncing, 5000));
        Thread th2 = new Thread(new SyncThread(syncing, 2000));

        th1.start();
        System.out.println(syncing.getValue());
        th2.start();
        System.out.println(syncing.getValue());
    }

}

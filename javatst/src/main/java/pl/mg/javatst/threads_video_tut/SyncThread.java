package pl.mg.javatst.threads_video_tut;

/**
 * Wątek z synchronizowanym dostępem do zmiennej.
 */
public class SyncThread implements Runnable {

    private SynchronizedObject sync;
    private int sleepTime;

    public SyncThread(SynchronizedObject object, int sleepTime) {
        this.sync = object;
        this.sleepTime = sleepTime;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(this.sleepTime);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        this.sync.add();
    }

    public SynchronizedObject getSync() {
        return sync;
    }

}

package pl.mg.javatst.threads;

public class SynchronizedObject {
    private int value = 0;

    public synchronized void add() {
        synchronized (this) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.value++;
        }
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}

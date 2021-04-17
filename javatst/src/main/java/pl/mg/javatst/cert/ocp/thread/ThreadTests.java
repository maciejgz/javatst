package pl.mg.javatst.cert.ocp.thread;

/**
 * This test shows how to put threads into waiting state using monitor object and how monitor releases threads
 * put into waiting state randomly.
 * Monitor object should be used in the synchronized code.
 */
public class ThreadTests {
    public static void main(String[] args) {
        Object monitor = new Object();
        Thread thread1 = new Thread(createRun("first", monitor));
        Thread thread2 = new Thread(createRun("second", monitor));
        Thread thread3 = new Thread(createRun("third", monitor));
        Thread thread4 = new Thread(createRun("fourth", monitor));
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        releaseThread(monitor);
        releaseThread(monitor);
        releaseThread(monitor);
        releaseThread(monitor);
        releaseThread(monitor);
    }

    private static void releaseThread(Object monitor) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (monitor) {
            monitor.notify();
        }
    }

    private static Runnable createRun(String name, Object monitor) {
        return new Runnable() {
            @Override
            public void run() {
                System.out.println("start " + name);
                try {
                    synchronized (monitor) {
                        monitor.wait();
                    }
                } catch (InterruptedException ex) {

                }
                System.out.println("finish " + name);
            }
        };
    }

}

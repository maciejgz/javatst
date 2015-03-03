package pl.mg.javatst.threads_video_tut;

import java.util.Random;

/**
 * Prosty sleep thread
 */
public class SleepThread implements Runnable {

    private String id;

    public SleepThread(String id) {
        this.id = id;
    }

    public void run() {
        System.out.println("thread_" + id + "-start");
        try {
            Thread.sleep((new Random()).nextInt(5000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("thread_" + id + "-stop");
    }

}

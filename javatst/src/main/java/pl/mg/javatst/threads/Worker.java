package pl.mg.javatst.threads;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by m on 2015-02-23.
 */
public class Worker {

    private Random random = new Random();

    private List<Integer> list1 = new ArrayList<>();
    private List<Integer> list2 = new ArrayList<>();

    public void stageOne() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        list1.add(random.nextInt(100));
    }

    public void stageTwo() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        list2.add(random.nextInt(100));
    }

    public void process() {
        for (int i = 0; i < 1000; i++) {
            stageOne();
            stageTwo();
        }
    }

    public static void main(String[] args) {
        System.out.println("Starting...");
        long start = System.nanoTime();

        Worker worker = new Worker();
        worker.process();

        long end = System.nanoTime();
        System.out.println((end - start) / (10 ^ 9));
        System.out.println(worker.list1.size() + ";" + worker.list2.size());
    }

    @Override
    public String toString() {
        return "Worker{" +
                "random=" + random +
                ", list1=" + list1 +
                ", list2=" + list2 +
                '}';
    }
}

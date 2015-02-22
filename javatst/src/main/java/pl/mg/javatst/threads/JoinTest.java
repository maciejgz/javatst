package pl.mg.javatst.threads;

/**
 * join() test
 * @author m
 *
 */
public class JoinTest {

    public static void main(String[] args) {
        Thread th1 = new Thread(new SleepThread("1"));

        Thread th2 = new Thread(new SleepThread("2"));
        th1.start();
        try {
            th1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("after join");

    }

}

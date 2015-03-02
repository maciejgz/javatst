package pl.mg.javatst.threads_video_tut;

import pl.mg.javatst.util.Println;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Created by m on 2015-03-01.
 */
public class SemaphoresTest {

    public static void main(String[] args) throws InterruptedException {


        Semaphore semaphore = new Semaphore(0);
        semaphore.release();
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        Println.print("Available permits: " + semaphore.availablePermits());


        Connection.getInstance().connect();
        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i < 200; i++) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    Connection.getInstance().connect();
                }
            });
        }

        executorService.shutdown();

        executorService.awaitTermination(1, TimeUnit.DAYS);
    }
}

class Connection {
    private static Connection instance = new Connection();

    private int connections = 0;

    //do zarządzania pulą kolekcji
    private Semaphore sem = new Semaphore(10);

    private Connection() {

    }

    public static Connection getInstance() {
        return instance;
    }

    public void connect() {

        //pobranie locka z semafora i blokownie dopóki żaden kolejny nie jest aktualny w określonym czasie.
        try {
            sem.tryAcquire(15, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Println.print("semaphore ");
            e.printStackTrace();
        }

        synchronized (this) {
            connections++;
            Println.print("Current conenctions: " + connections);
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        synchronized (this) {
            connections--;
        }
        //zwalnianie semafora
        sem.release();


    }
}

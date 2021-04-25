package pl.mg.javatst.cert.ocp;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceSample {
    public static void main(String[] args) {
        ExecutorService service = Executors.newSingleThreadExecutor();
        service.submit(() -> {
            throw new RuntimeException();
        });
        service.submit(() -> {
            try {
                Thread.sleep(1000);

            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
            System.out.println("task 1");
        });
        service.submit(() -> System.out.println("task2"));
        service.shutdown();
    }


}

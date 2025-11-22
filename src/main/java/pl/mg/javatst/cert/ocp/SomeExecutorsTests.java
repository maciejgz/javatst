package pl.mg.javatst.cert.ocp;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

@Slf4j
public class SomeExecutorsTests {

    public static void main(String[] args) {
        ExecutorService exec = Executors.newSingleThreadExecutor();
        Future<String> result = exec.submit(() -> {
            Thread.sleep(1000);
            return "finished";
        });

        try {
            System.out.println(result.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        ExecutorService exec2 = Executors.newFixedThreadPool(50);

        List<Callable<String>> calls = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            calls.add(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    int result = (new Random()).nextInt(5000);
                    Thread.sleep(result);
                    return "firstFinished in " + result;
                }
            });
        }

        try {
            System.out.println(exec2.invokeAny(calls));
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        exec.shutdown();
        exec2.shutdown();
    }
}

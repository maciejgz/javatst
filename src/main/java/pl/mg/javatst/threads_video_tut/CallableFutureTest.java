package pl.mg.javatst.threads_video_tut;

import pl.mg.javatst.util.Println;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.*;

/**
 * Użycie obiektu callable ze zwracanym obiektem future.
 * Created by m on 2015-03-01.
 */
public class CallableFutureTest {


    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        Future<Integer> future = executorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {

                Random random = new Random();


                Integer duration = random.nextInt(4000);
                if (duration > 2000) {
                    throw new IOException();
                }
                Thread.sleep(duration);

                return duration;
            }
        });

        executorService.shutdown();
        try {
            //get blokuje wątek główny do czasu uzyskania odpowiedzi.
            Println.print("Result is: " + future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}

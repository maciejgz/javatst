package pl.mg.javatst.java8.lambda;

import pl.mg.javatst.java8.behaviourparametrization.BehaviourParametrization;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Java 8 in Action - rozdział 3 Lambda expressions
 */
public class Lambda8Sample {


    public static List<Apple> lambdaSyntax() {

        List<Apple> appleList = new ArrayList<>();

        appleList.add(new Apple("blue", 120));
        appleList.add(new Apple("green", 60));
        appleList.add(new Apple("red", 15));


        List<Apple> result = BehaviourParametrization.filter(appleList, (Apple a) -> "green".equals(a.getColor()));


        return result;
    }

    public static String lambdaInCallable() {
        String result = "";
        ExecutorService service = Executors.newFixedThreadPool(1);

        Future<String> task = service.submit(() -> {
            return "result";
        });
        try {
            result = task.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return result;
    }

}

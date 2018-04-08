package pl.mg.javatst.java8.lambda;

import pl.mg.javatst.java8.behaviourparametrization.BehaviourParametrization;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Function;

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

    /**
     * użycie interfejsu funcyjnego function możliwego do przekazania jako parametr w requescie
     *
     * @param list
     * @param fstajesz z
     * @param <T>
     * @param <R>
     */
    public static <T, R> List<R> map(List<T> list, Function<T, R> f) {
        List<R> result = new ArrayList<>();
        for (T s : list) {
            result.add(f.apply(s));
        }
        return result;
    }


    public static void myTest() {

        Checker<Integer> isOddLambda = object -> object % 2 != 0;

        System.out.println(isOddLambda.check(123));
        System.out.println(isOddLambda.check(124));

    }

    public interface Checker<T> {
        boolean check(T object);
    }

    public static void sortListTest() {
        List<String> content = Arrays.asList("tesds", "m2343", "4tesds", "test2");
        content.forEach(n -> System.out.println(n));
        Collections.sort(content, (p1, p2) -> p1.compareTo(p2));
        System.out.println("after sorting");
        content.forEach(n -> System.out.println(n));
    }

}

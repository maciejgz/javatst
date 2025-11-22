package pl.mg.javatst.cert.ocp;

import java.lang.annotation.Retention;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTests {

    public static void main(String[] args) {
        List<Integer> arr = Arrays.asList(1, 2, 3, 4, 5);
        System.out.println("count " + arr.stream().count());
        System.out.println("max " + arr.stream().max(Integer::compareTo).get());
        System.out.println("min " + arr.stream().min(Integer::compareTo).get());

        arr.stream().findAny().ifPresent(w -> {
            System.out.println("findAny " + w);
        });


        System.out.println(arr.stream().reduce((integer, integer2) -> {
            return integer + integer2;
        }).get());


        System.out.println(arr.stream().reduce(1, (integer, integer2) -> integer * integer2));

        //collect
        System.out.println(arr.stream().collect(() -> {
            return new HashMap<Integer, Integer>();
        }, (a, b) -> {
            a.put(b, b * b);
        }, (a, b) -> {
            a.putAll(b);
        }));

        System.out.println("collector toList: " + arr.stream().collect(Collectors.toList()));


        //intermediate operations
        //filter
        arr.stream().filter(w -> w % 2 == 0).forEach(System.out::print);
        System.out.println("");

        //limit
        arr.stream().limit(2).forEach(System.out::print);
        System.out.println("");

        //skip
        arr.stream().skip(2).forEach(System.out::print);


        //map
        System.out.println("mapped: " + arr.stream().map(i -> i * 2).collect(Collectors.toList()));

        //peek - nie zmienia streamu źródłowego
        System.out.println("peek: " + arr.stream().peek(w -> w = 2).collect(Collectors.toList()));


        //tasks
        Stream<String> streamT1 = Stream.iterate("", (s) -> s + "1");
        System.out.println("streamT1 " + streamT1.limit(2).peek(System.out::println).map(x -> x + "2").collect(Collectors.toList()));

        StreamTests.reduceTests();
    }

    private static void reduceTests() {
        List<Duck> ducks = new ArrayList<>();
        ducks.add(new Duck("sam", 15));
        ducks.add(new Duck("john", 56));
        ducks.add(new Duck("Alan", 2));
        ducks.add(new Duck("Bob", 2));

        Duck reduced = ducks.stream().reduce(new Duck("", 0), (result, element) -> {
            result.setName(result.getName() + element.getName());
            result.setWeight(result.getWeight() + element.getWeight());
            return result;
        });
        System.out.println("reduce test result: " + reduced.toString());

        Map<Integer, List<Duck>> collect = ducks.stream().collect(Collectors.groupingBy(Duck::getWeight));
        for (Integer integer : collect.keySet()) {
            System.out.println("duck with weight: " + integer + " exist: " + collect.get(integer).size());
        }


        Path p = Path.of("mango");
        p.resolve("apple");
        Path p2 = p.resolve("guava");
        System.out.println(p2 + "" + p);


        Path path = Path.of("aaa").normalize();

        var i = 100;
        var s = "A" + i;

        List genericList = new ArrayList();
        genericList.add(new Duck("aa", 15));
        for (Object object : genericList) {
            System.out.println(object.toString());
        }

        Callable<String> call = () -> "aaa";
        try {
            ExecutorService executorService = Executors.newFixedThreadPool(1);
            Future<String> submit = executorService.submit(call);
            System.out.println(submit.get());
            executorService.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<? super Duck> ducksss = new ArrayList<>();
        ducksss.add(new Duck("aasas", 123));

        for (Object o : ducksss) {
            System.out.println(o.toString());
        }
    }
}

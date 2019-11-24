package pl.mg.javatst.cert.ocp;

import java.util.function.*;

public class SupplierTests {
    public static void main(String[] args) {
        Supplier<String> sup1 = () -> {
            return "123";
        };

        sup1.get();

        Consumer<String> consumer1 = x -> System.out.println(x);
        consumer1.accept("sample");

        Predicate<String> pred1 = (String s1) -> {
            return s1.isEmpty();
        };

        pred1.test("");

        Function<String, String> fun1 = (String a) -> {
            return a + "sample";
        };
        fun1.apply("sa");


        UnaryOperator<String> un1 = a -> a + a;
        un1.apply("sa");

    }


}

package pl.mg.javatst.certificate.oca.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class PredicateSearch {

    public static void main(String[] args) {
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("fish", false, true));
        animals.add(new Animal("kangaroo", true, false));
        animals.add(new Animal("rabbit", true, false));
        animals.add(new Animal("turtle", false, true));

        PredicateSearch.print(animals, a -> a.isCanHop());

        //zapis lambdy do zmiennej
        Predicate<Animal> canHopPredicate = a -> a.isCanHop();
        PredicateSearch.print(animals, canHopPredicate);

        PredicateSearch.arrayListRemoveTest();
    }

    private static void print(List<Animal> animals, Predicate<Animal> checker) {
        for (Animal animal : animals) {
            if (checker.test(animal)) {
                System.out.println(animal);
            }
        }
    }

    /**
     * Test metody array list, która przyjmuje predicate i usuwa wszystko
     */
    private static void arrayListRemoveTest() {
        List<String> anims = new ArrayList<>();
        anims.add("long ear");
        anims.add("flopp");
        anims.add("hoppy");
        System.out.println(anims);
        //przekazanie body metody predicate do
        anims.removeIf(s -> s.charAt(0) != 'h');
        System.out.println(anims);

    }
}

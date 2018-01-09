package pl.mg.javatst.java8.behaviourparametrization;

import java.util.ArrayList;
import java.util.List;

/**
 * Chapter 2 - pozwala na przekazywanie wielu parametrów do
 * metody ze zmienną ich
 * ilością w zależności od zachowania
 */
public class BehaviourParametrization {

    /**
     * The worst filtering method. Bez żadnych parametrów w metodzie
     *
     * @param inventory
     * @return
     */
    public static List<BehaviourApple> filterGreenApple(List<BehaviourApple> inventory) {
        List<BehaviourApple> result = new ArrayList<>();
        for (BehaviourApple apple2 : inventory) {
            if ("green".equals(apple2.getColor())) {
                result.add(apple2);
            }
        }
        return result;
    }

    /**
     * Simple parameter
     *
     * @param inventory
     * @param color
     * @return
     */
    public static List<BehaviourApple> filterAppleByColor(List<BehaviourApple> inventory, String color) {
        List<BehaviourApple> result = new ArrayList<>();
        for (BehaviourApple apple2 : inventory) {
            if (color.equals(apple2.getColor())) {
                result.add(apple2);
            }
        }
        return result;
    }

    /**
     * Filtrowanie po wadze. Totalne łamanie zasady DRY
     *
     * @param inventory
     * @param weight
     * @return
     */
    public static List<BehaviourApple> filterApplesByWeight(List<BehaviourApple> inventory, int weight) {
        List<BehaviourApple> result = new ArrayList<>();
        for (BehaviourApple apple2 : inventory) {
            if (apple2.getWeight() > weight) {
                result.add(apple2);
            }
        }
        return result;
    }


    //podejśćie siódme
    public interface Predicate<T> {
        boolean test(T t);
    }


    public static <T> List<T> filter(List<T> list, Predicate<T> p) {
        List<T> result = new ArrayList<>();
        for (T e : list) {
            if (p.test(e)) {
                result.add(e);
            }
        }
        return result;
    }

}

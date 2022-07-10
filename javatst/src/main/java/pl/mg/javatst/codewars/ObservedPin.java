package pl.mg.javatst.codewars;

import java.util.*;
import java.util.stream.Collectors;

public class ObservedPin {

    private static final List<String> ZERO = new ArrayList<>();
    private static final List<String> ONE = new ArrayList<>();
    private static final List<String> TWO = new ArrayList<>();
    private static final List<String> THREE = new ArrayList<>();
    private static final List<String> FOUR = new ArrayList<>();
    private static final List<String> FIVE = new ArrayList<>();
    private static final List<String> SIX = new ArrayList<>();
    private static final List<String> SEVEN = new ArrayList<>();
    private static final List<String> EIGHT = new ArrayList<>();
    private static final List<String> NINE = new ArrayList<>();

    private static final Map<Integer, List<String>> ENTRIES = new HashMap<>();

    static {

        ZERO.add("0");
        ZERO.add("8");
        ENTRIES.put(0, ZERO);

        ONE.add("1");
        ONE.add("2");
        ONE.add("4");
        ENTRIES.put(1, ONE);

        TWO.add("2");
        TWO.add("1");
        TWO.add("3");
        TWO.add("5");
        ENTRIES.put(2, TWO);

        THREE.add("3");
        THREE.add("2");
        THREE.add("6");
        ENTRIES.put(3, THREE);

        FOUR.add("4");
        FOUR.add("1");
        FOUR.add("5");
        FOUR.add("7");
        ENTRIES.put(4, FOUR);


        FIVE.add("5");
        FIVE.add("2");
        FIVE.add("4");
        FIVE.add("6");
        FIVE.add("8");
        ENTRIES.put(5, FIVE);

        SIX.add("6");
        SIX.add("3");
        SIX.add("5");
        SIX.add("9");
        ENTRIES.put(6, SIX);

        SEVEN.add("7");
        SEVEN.add("4");
        SEVEN.add("8");
        ENTRIES.put(7, SEVEN);

        EIGHT.add("8");
        EIGHT.add("5");
        EIGHT.add("7");
        EIGHT.add("9");
        EIGHT.add("0");
        ENTRIES.put(8, EIGHT);

        NINE.add("9");
        NINE.add("6");
        NINE.add("8");
        ENTRIES.put(9, NINE);
    }


    public static void main(String[] args) {
        getPINs("123");
        getPINs("8");
    }

    public static List<String> getPINs(String observed) {
        List<String> result;
        Set<String> values = new HashSet<>();
        List<Integer> ingested = observed.chars().mapToObj(i -> (char) i).map(value -> Integer.valueOf(String.valueOf(value))).collect(Collectors.toList());
        findVariations(values, ingested, 0);


        result = values.stream().sorted().collect(Collectors.toList());

        for (String value : values) {
            System.out.println(result);
        }
        return result;
    }

    private static void findVariations(Set<String> result, List<Integer> input, int index) {
        if (index >= input.size()) {
            return;
        }

        for (String value : ENTRIES.get(input.get(index))) {
            //ostatnia liczba i wpisanie do setu
            if (index == input.size() - 1) {
                result.add(generateResult(input, index, value));
            } else {
                //liczba w środku - utworzenie wariacji i wywołanie ponowne
                List<Integer> newList = new ArrayList<>(input);
                newList.set(index, Integer.parseInt(value));
                findVariations(result, newList, index + 1);
            }
        }

    }


    private static String generateResult(List<Integer> input, int index, String valueAtIndex) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < input.size(); i++) {
            if (i == index) {
                str.append(valueAtIndex);
            } else {
                str.append(input.get(i));
            }
        }
        return str.toString();
    }


}

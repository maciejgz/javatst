package pl.mg.javatst.codility;

import java.util.ArrayList;
    import java.util.Arrays;
    import java.util.HashMap;
    import java.util.List;
    import java.util.Map;

/**
 * https://www.codewars.com/kata/5263c6999e0f40dee200059d/train/java
 * done.
 */
public class TheObservedPin {

    private static final Map<Integer, List<Integer>> possibilities = new HashMap<>();

    static {
        possibilities.put(1, Arrays.asList(1, 2, 4));
        possibilities.put(2, Arrays.asList(1, 2, 3, 5));
        possibilities.put(3, Arrays.asList(2, 3, 6));
        possibilities.put(4, Arrays.asList(1, 4, 5, 7));
        possibilities.put(5, Arrays.asList(2, 4, 5, 6, 8));
        possibilities.put(6, Arrays.asList(3, 5, 6, 9));
        possibilities.put(7, Arrays.asList(4, 7, 8));
        possibilities.put(8, Arrays.asList(5, 7, 8, 9, 0));
        possibilities.put(9, Arrays.asList(6, 9, 8));
        possibilities.put(0, Arrays.asList(0, 8));

    }

    public static List<String> getPINs(String observed) {
        List<String> results = new ArrayList<>();
        int firstValue = Integer.parseInt(String.valueOf(observed.charAt(0)));
        if (observed.length() > 1) {
            getRec("", firstValue,
                    observed.substring(1), results);
        } else {
            getRec("", firstValue,
                    "", results);
        }
        return results;
    }

    private static void getRec(String prefix,
            int digit,
            String postfix,
            List<String> results) {
        if (postfix != null && postfix.length() > 0) {
            for (Integer possibility : possibilities.get(digit)) {
                int firstValue = Integer.parseInt(String.valueOf(postfix.charAt(0)));
                if (postfix.length() > 1) {
                    getRec(prefix + possibility,
                            firstValue,
                            postfix.substring(1),
                            results);
                } else {
                    getRec(prefix + possibility,
                            firstValue,
                            "",
                            results);
                }
            }
        }
        //last step
        if (postfix != null && postfix.length() == 0) {
            for (Integer possibility : possibilities.get(digit)) {
                results.add(prefix + possibility);
            }
        }
    }

}

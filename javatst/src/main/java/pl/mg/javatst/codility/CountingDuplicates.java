package pl.mg.javatst.codility;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class CountingDuplicates {

    public static int duplicateCount(String text) {
        long result = 0;
        Map<Character, Integer> maps = new HashMap<>();
        for (char input : text.toLowerCase().toCharArray()) {
            if (maps.containsKey(input)) {
                int number = maps.get(input);
                maps.put(input, number + 1);
            } else {
                maps.put(input, 1);
            }
        }
        result = maps.values().stream().filter(a -> a > 1).count();
        return (int) result;
    }

}

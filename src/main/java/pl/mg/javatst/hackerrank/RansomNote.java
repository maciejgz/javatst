package pl.mg.javatst.hackerrank;

import java.util.HashMap;
import java.util.Map;

/**
 * https://www.hackerrank.com/challenges/ctci-ransom-note
 */
public class RansomNote {


    static void checkMagazine(String[] magazine, String[] note) {
        Map<String, Integer> words = new HashMap<>();
        for (String mag : magazine) {
            if (!words.containsKey(mag)) {
                words.put(mag, 1);
            } else {
                int num = words.get(mag);
                words.put(mag, num + 1);
            }
        }
        String res = "Yes";
        for (String s : note) {
            if (!words.containsKey(s)) {
                res = "No";
            } else {
                if (words.get(s) == 1) {
                    words.remove(s);
                } else {
                    int num = words.get(s);
                    words.put(s, num - 1);
                }
            }
        }
        System.out.println(res);
    }
}

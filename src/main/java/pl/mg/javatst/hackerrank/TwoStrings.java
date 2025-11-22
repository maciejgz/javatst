package pl.mg.javatst.hackerrank;

import java.util.HashSet;
import java.util.Set;

/**
 * https://www.hackerrank.com/challenges/two-strings
 */
public class TwoStrings {

    static String twoStrings(String s1, String s2) {
        char[] s2Chars = s2.toCharArray();
        Set<Character> str1 = new HashSet<>();
        for (char c : s1.toCharArray()) {
            str1.add(c);
        }
        String res = "NO";
        for (char s2Char : s2Chars) {
            if (str1.contains(s2Char)) {
                res = "YES";
                break;
            }
        }
        return res;
    }

}

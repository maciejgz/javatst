package pl.mg.javatst.hackerrank;

/**
 * https://www.hackerrank.com/challenges/alternating-characters/
 * 20 points (max)
 */
public class AlternatingCharacters {

    static int alternatingCharacters(String s) {
        int result = 0;
        if (s.length() < 2) {
            return result;
        }
        for (int i = 0; i < s.length() - 1; i++) {
            while (i + 1 < s.length() && s.charAt(i) == s.charAt(i + 1)) {
                result++;
                i++;
            }
        }
        return result;
    }
}

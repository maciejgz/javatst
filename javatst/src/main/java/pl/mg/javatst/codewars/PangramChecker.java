package pl.mg.javatst.codewars;

import java.util.HashMap;
import java.util.Map;

public class PangramChecker {

    public static void main(String[] args) {
        String pangram2 = "The quick brown fox jumps over the lazy dog.";
        System.out.println(check(pangram2));
    }

    public static boolean check(String sentence) {
        Map<String, Boolean> alphabet = new HashMap<>();
        alphabet.put("a", false);
        alphabet.put("b", false);
        alphabet.put("c", false);
        alphabet.put("d", false);
        alphabet.put("e", false);
        alphabet.put("f", false);
        alphabet.put("g", false);
        alphabet.put("h", false);
        alphabet.put("i", false);
        alphabet.put("j", false);
        alphabet.put("k", false);
        alphabet.put("l", false);
        alphabet.put("m", false);
        alphabet.put("n", false);
        alphabet.put("o", false);
        alphabet.put("p", false);
        alphabet.put("r", false);
        alphabet.put("q", false);
        alphabet.put("s", false);
        alphabet.put("t", false);
        alphabet.put("u", false);
        alphabet.put("v", false);
        alphabet.put("w", false);
        alphabet.put("z", false);

        for (char character : sentence.toCharArray()) {
            alphabet.put(String.valueOf(character).toLowerCase(), true);
        }
        return alphabet.values().stream().allMatch(aBoolean -> aBoolean);
    }
}

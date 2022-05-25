package pl.mg.javatst.codewars;

import java.util.ArrayList;
import java.util.List;

public class PigLatin {

    public static String pigIt(String str) {
        String add = "ay";
        String[] words = str.split(" ");
        List<String> wordsResult = new ArrayList<>();

        for (String word : words) {
            if (word.matches("([!|,|.|||?|]|,|/|\\\\)")) {
                wordsResult.add(word);
            } else {
                if (word.length() > 1) {
                    wordsResult.add(word.trim().substring(1) + word.trim().charAt(0) + add);
                } else {
                    wordsResult.add(word.trim() + add);
                }
            }
        }

        return String.join(" ", wordsResult);
    }
}

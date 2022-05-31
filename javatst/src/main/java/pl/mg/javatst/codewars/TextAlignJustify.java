package pl.mg.javatst.codewars;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TextAlignJustify {

    public static void main(String[] args) {

    }


    public static String justify(String text, int width) {

        List<String> words = Arrays.asList(text.split(" "));
        StringBuilder result = new StringBuilder();
        int currentSize = 0;
        List<String> wordsInLine = new ArrayList<>();
        for (String word : words) {
            wordsInLine.add(word);
            currentSize += word.length();

            if()
        }
        return text;
    }
}

package pl.mg.javatst.codewars;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class BurrowsWheeler {

    public static void main(String[] args) {
        encodeTests();
        decodeTests();
    }

    private static void encodeTests() {
        System.out.println(encode("bananabar"));
        System.out.println(encode("Humble Bundle"));
        System.out.println(encode("Mellow Yellow"));
    }

    private static void decodeTests() {
        System.out.println("1: " + decode("nnbbraaaa", 4));
        System.out.println("2: " + decode("e emnllbduuHB", 2));
        System.out.println("3: " + decode("ww MYeelllloo", 1));

    }

    public static BWT encode(String s) {
        List<String> words = new ArrayList<>();

        for (int i = 0; i < s.length(); i++) {
            words.add(s.substring(i) + s.substring(0, i));
        }

        Collections.sort(words);
        int index = -1;
        StringBuilder lastColumnWord = new StringBuilder();
        for (int i = 0; i < words.size(); i++) {
            if (words.get(i).equals(s)) {
                index = i;
            }
            lastColumnWord.append(words.get(i).charAt(s.length() - 1));
        }

        return new BWT(lastColumnWord.toString(), index);
    }

    public static String decode(String s, int n) {
        if (n == -1 || s.isEmpty()) {
            return "";
        }
        List<StringBuilder> list = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            list.add(new StringBuilder());
        }
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < s.length(); j++) {
                list.get(j).insert(0, s.charAt(j));
            }
            list = list.stream().sorted(Comparator.comparing(StringBuilder::toString)).collect(Collectors.toList());
        }
        return list.get(n).toString();
    }


}

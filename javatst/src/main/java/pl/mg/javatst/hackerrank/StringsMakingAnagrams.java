package pl.mg.javatst.hackerrank;

import java.util.HashMap;
import java.util.Map;

/**
 * https://www.hackerrank.com/challenges/ctci-making-anagrams/
 * 25 points (max)
 */
public class StringsMakingAnagrams {

    static int makeAnagram(String a, String b) {
        int result = 0;

        Map<Character, Integer> aMap = new HashMap<>();
        Map<Character, Integer> bMap = new HashMap<>();

        char[] chars = a.toCharArray();
        for (char aChar : chars) {
            if (!aMap.containsKey(aChar)) {
                aMap.put(aChar, 1);
            } else {
                int aVal = aMap.get(aChar);
                aMap.put(aChar, aVal + 1);
            }
        }
        char[] charsB = b.toCharArray();
        for (char aChar : charsB) {
            if (!bMap.containsKey(aChar)) {
                bMap.put(aChar, 1);
            } else {
                int aVal = bMap.get(aChar);
                bMap.put(aChar, aVal + 1);
            }
        }

        for (Character character : aMap.keySet()) {
            if(bMap.containsKey(character)) {
                result += Math.abs(aMap.get(character) - bMap.get(character));
            } else {
                result += aMap.get(character);
            }
        }
        for (Character character : bMap.keySet()) {
            if(!aMap.containsKey(character)) {
                result += bMap.get(character);
            }
        }
        return result;
    }

}

package pl.mg.javatsts.leetcode;


import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class WordPattern7 {

    public static void main(String[] args) {
        WordPattern7 pattern7 = new WordPattern7();
        System.out.println(pattern7.wordPattern("abba", "dog dog dog dog"));
    }

    public boolean wordPattern(String pattern, String s) {
        String[] values = s.split(" ");
        char[] patternIds = pattern.toCharArray();
        if (patternIds.length == 0 || patternIds.length != values.length) {
            return false;
        }
        Map<Character, String> patterns = new HashMap<>();
        int i = 0;
        for (char patternId : patternIds) {
            if (patterns.containsKey(patternId)
                    && !values[i].equals(patterns.get(patternId))) {
                return false;
            }
            for (Character character : patterns.keySet()) {
                if (patternId != character && patterns.get(character).equals(values[i])) {
                    return false;
                }
            }
            patterns.put(patternId, values[i]);
            i++;
        }
        return true;
    }
}

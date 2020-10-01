package pl.mg.javatsts.leetcode;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/explore/challenge/card/september-leetcoding-challenge/558/week-5-september-29th-september-30th/3477/
 */
@Slf4j
public class WordBreak29 {

    public boolean wordBreak(String s, List<String> wordDict) {
        int[] pos = new int[s.length() + 1];
        Arrays.fill(pos, -1);

        pos[0] = 0;

        for (int i = 0; i < s.length(); i++) {
            if (pos[i] != -1) {
                for (int j = i + 1; j <= s.length(); j++) {
                    String sub = s.substring(i, j);
                    if (wordDict.contains(sub)) {
                        pos[j] = i;
                    }
                }
            }
        }

        return pos[s.length()] != -1;
    }


}

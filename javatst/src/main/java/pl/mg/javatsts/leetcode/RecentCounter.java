package pl.mg.javatsts.leetcode;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * https://leetcode.com/explore/featured/card/october-leetcoding-challenge/559/week-1-october-1st-october-7th/3480/
 */
public class RecentCounter {

    private final Map<Integer, Integer> calls;

    public RecentCounter() {
        calls = new HashMap<>();
    }

    public int ping(int t) {
        if (calls.containsKey(t)) {
            calls.put(t, calls.get(t) + 1);
        } else {
            calls.put(t, 1);
        }
        int result = 0;

        for (Iterator<Integer> it = calls.keySet().iterator(); it.hasNext(); ) {
            Integer time = it.next();
            if ((time >= t - 3000) && (time <= t)) {
                result += calls.get(time);
            } else {
                it.remove();
            }
        }


        return result;
//        return calls.keySet().stream().filter(time -> (time >= t - 3000) && (time <= t)).mapToInt(calls::get).sum();
    }

}

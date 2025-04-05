package pl.mg.javatst.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/explore/challenge/card/october-leetcoding-challenge/559/week-1-october-1st-october-7th/3481/
 * Given an array of distinct integers candidates and a target integer target, return a list of all unique combinations
 * of candidates where the chosen numbers sum to target. You may return the combinations in any order.
 * <p>
 * The same number may be chosen from candidates an unlimited number of times.
 * Two combinations are unique if the frequency of at least one of the chosen numbers is different.
 */
public class CombinationSum {

    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] num, int target) {
        List<Integer> list = new ArrayList<>();
        Arrays.sort(num);
        dfs(num, 0, list, 0, target);
        return res;
    }

    private void dfs(int[] num, int start, List<Integer> result, int sum, int target) {
        if (sum == target) {
            res.add(new ArrayList<>(result));
            return;
        }
        for (int i = start; i < num.length; i++) {
            if (i > start && num[i] == num[i - 1]) {
                continue;
            }
            if (num[i] + sum > target) {
                break;
            }

            result.add(num[i]);
            dfs(num, i, result, sum + num[i], target);
            result.remove(result.size() - 1);
        }

    }

}

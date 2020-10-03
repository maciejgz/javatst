package pl.mg.javatsts.leetcode;

import org.apache.commons.collections4.list.TreeList;

import java.util.*;
import java.util.stream.Collectors;

/**
 * https://leetcode.com/explore/challenge/card/october-leetcoding-challenge/559/week-1-october-1st-october-7th/3482/
 * <p>
 * Given an array of integers nums and an integer k, return the number of unique k-diff pairs in the array.
 * <p>
 * A k-diff pair is an integer pair (nums[i], nums[j]), where the following are true:
 * <p>
 * 0 <= i, j < nums.length
 * i != j
 * a <= b
 * b - a == k
 */
public class KDiffPairsArray {

    public int findPairs(int[] nums, int k) {
        HashSet<String> resultSet = new HashSet<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (i != j
                        && nums[i] <= nums[j]
                        && nums[j] - nums[i] == k) {
                    resultSet.add(nums[i] + ":" + nums[j]);
                }
            }
        }
        return resultSet.size();
    }

}

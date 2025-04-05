package pl.mg.javatst.leetcode;

import java.util.Arrays;

/**
 * Given an unsorted integer array, find the smallest missing positive integer.
 * <p>
 * Example 1:
 * <p>
 * Input: [1,2,0]
 * Output: 3
 * <p>
 * https://leetcode.com/explore/challenge/card/september-leetcoding-challenge/558/week-5-september-29th-september-30th/3478/
 */
public class FirstMissingPositive30 {

    public int firstMissingPositive(int[] nums) {
        int result = 0;
        Arrays.sort(nums);
        for (int num : nums) {
            if (num > 0 && num > result) {
                if (num == result + 1) {
                    result++;
                } else if (num > result + 1) {
                    result++;
                    return result;
                }
            }
        }
        result++;
        return result;
    }
}

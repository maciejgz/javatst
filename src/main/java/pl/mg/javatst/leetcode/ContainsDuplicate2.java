package pl.mg.javatst.leetcode;

import lombok.extern.slf4j.Slf4j;

/**
 * https://leetcode.com/explore/challenge/card/september-leetcoding-challenge/554/week-1-september-1st-september-7th/3446/
 */
@Slf4j
public class ContainsDuplicate2 {


    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (nums.length < 2) {
            return false;
        }

        return false;
    }


    /**
     * indices i and j in the array such that the absolute difference between nums[i] and nums[j] is at most t
     * and the absolute difference between i and j is at most k.
     */
    public boolean containsNearbyAlmostDuplicateBruteForce(int[] nums, int k, int t) {
        if (nums.length < 2) {
            return false;
        }
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (Math.abs((long) nums[i] - (long) nums[j]) <= t
                        && Math.abs(i - j) <= k) {
                    return true;
                }
            }
        }
        return false;
    }
}

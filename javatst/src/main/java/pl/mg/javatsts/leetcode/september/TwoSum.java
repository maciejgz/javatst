package pl.mg.javatsts.leetcode.september;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/two-sum/
 */
@Data
public class TwoSum {

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> input = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            input.put(nums[i], i);
        }

        for (int i = 0; i < nums.length; i++) {
            int minus = target - nums[i];
            if (input.containsKey(minus) && input.get(minus) != i) {
                return new int[]{i, input.get(minus)};
            }
        }
        return null;
    }

}

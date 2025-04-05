package pl.mg.javatst.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 57. Insert Interval
 * https://leetcode.com/problems/insert-interval/
 */
public class InsertInterval {

    public static void main(String[] args) {
        InsertInterval i = new InsertInterval();
        int[][] intervals1 = {{1, 3}, {6, 9}};
        int[] newInterval1 = {2, 5};
        int[][] intervals2 = {{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}};
        int[] newInterval2 = {4, 8};

        int[][] result1 = i.insert(intervals1, newInterval1);
        int[][] result2 = i.insert(intervals2, newInterval2);

        for (int[] ints : result1) {
            System.out.println(ints[0] + "-" + ints[1]);
        }

        for (int[] ints : result2) {
            System.out.println(ints[0] + "-" + ints[1]);
        }
    }

    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> result = new ArrayList<>();
        int i = 0;
        int n = intervals.length;

        // Add all intervals before the new interval
        while (i < n && intervals[i][1] < newInterval[0]) {
            result.add(intervals[i]);
            i++;
        }

        // Merge overlapping intervals with the new interval
        while (i < n && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }
        result.add(newInterval);

        // Add all intervals after the new interval
        while (i < n) {
            result.add(intervals[i]);
            i++;
        }

        return result.toArray(new int[result.size()][]);
    }
}

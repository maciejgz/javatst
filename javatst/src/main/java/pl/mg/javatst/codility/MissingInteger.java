package pl.mg.javatst.codility;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

/**
 * Write a function:
 * <p>
 * class Solution { public int solution(int[] A); }
 * <p>
 * that, given an array A of N integers, returns
 * the smallest positive integer (greater than 0) that does not occur in A.
 * <p>
 * For example, given A = [1, 3, 6, 4, 1, 2], the
 * function should return 5.
 * <p>
 * Given A = [1, 2, 3], the function should return 4.
 * <p>
 * Given A = [−1, −3], the function should return 1.
 * <p>
 * Assume that:
 * <p>
 * N is an integer within the range [1..100,000];
 * each element of array A is an integer within
 * the range [−1,000,000..1,000,000].
 * Complexity:
 * <p>
 * expected worst-case time complexity is O(N);
 * expected worst-case space complexity is O(N),
 * beyond input storage (not counting the storage required for input arguments).
 * 100%
 */
public class MissingInteger {

    public static void main(String[] args) {
        MissingInteger missingInteger = new MissingInteger();
        System.out.println(missingInteger.solution(new int[]{4, 5, 6, 2}));
    }

    public int solution(int[] A) {
        int result = 0;
        HashSet<Integer> list = new HashSet<>();

        for (int i = 0; i < A.length; i++) {
            if (A[i] > 0) {
                list.add(A[i]);
            }
        }
        List<Integer> sortedList = new ArrayList(list);
        Collections.sort(sortedList);
        for (int j = 0; j < sortedList.size(); j++) {
            if (sortedList.get(j) != (j + 1)) {
                result = j + 1;
                break;
            }
        }

        if (sortedList.size() == 0) {
            result = 1;
        }
        if (result == 0) {
            result = sortedList.size() + 1;
        }
        return result;
    }


}

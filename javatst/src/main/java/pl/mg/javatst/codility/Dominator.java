package pl.mg.javatst.codility;

import java.util.HashMap;
import java.util.Map;

/**
 * Dominator:
 * An array A consisting of N integers is given. The dominator of array A is the value that occurs in more than half of
 * the elements of A.
 * <p>
 * For example, consider array A such that
 * <p>
 * A[0] = 3    A[1] = 4    A[2] =  3
 * A[3] = 2    A[4] = 3    A[5] = -1
 * A[6] = 3    A[7] = 3
 * The dominator of A is 3 because it occurs in 5 out of 8 elements of A (namely in those with indices 0, 2, 4, 6 and 7)
 * and 5 is more than a half of 8.
 * <p>
 * Write a function
 * <p>
 * class Solution { public int solution(int[] A); }
 * <p>
 * that, given an array A consisting of N integers, returns index of any element of array A in which the dominator of A occurs.
 * The function should return −1 if array A does not have a dominator.
 * <p>
 * For example, given array A such that
 * <p>
 * A[0] = 3    A[1] = 4    A[2] =  3
 * A[3] = 2    A[4] = 3    A[5] = -1
 * A[6] = 3    A[7] = 3
 * the function may return 0, 2, 4, 6 or 7, as explained above.
 * <p>
 * Write an efficient algorithm for the following assumptions:
 * <p>
 * N is an integer within the range [0..100,000];
 * each element of array A is an integer within the range [−2,147,483,648..2,147,483,647].
 */
public class Dominator {

    public static void main(String[] args) {
        int[] array1 = new int[]{3, 4, 3, 2, 3, -1, 3, 3};
        int[] array2 = new int[]{1};
        System.out.println(solution(array1));
        System.out.println(solution(array2));
    }

    public static int solution(int[] A) {
        Map<Integer, Integer> elements = new HashMap<>();

        if (A.length == 1) {
            return 0;
        }

        for (int i = 0; i < A.length; i++) {
            if (elements.containsKey(A[i])) {
                elements.put(A[i], elements.get(A[i]) + 1);
                if (elements.get(A[i]) > A.length / 2) {
                    return i;
                }
            } else {
                elements.put(A[i], 1);
            }
        }
        return -1;
    }
}

package pl.mg.javatst.codility;

/**
 * A non-empty zero-indexed array A consisting of N integers is given.
 * <p/>
 * A permutation is a sequence containing each element from 1 to N once, and only once.
 * <p/>
 * For example, array A such that:
 * <p/>
 * A[0] = 4
 * A[1] = 1
 * A[2] = 3
 * A[3] = 2
 * is a permutation, but array A such that:
 * <p/>
 * A[0] = 4
 * A[1] = 1
 * A[2] = 3
 * is not a permutation, because value 2 is missing.
 * <p/>
 * The goal is to check whether array A is a permutation.
 * <p/>
 * Write a function:
 * <p/>
 * class Solution { public int solution(int[] A); }
 * <p/>
 * that, given a zero-indexed array A, returns 1 if array A is a permutation and 0 if it is not.
 * <p/>
 * For example, given array A such that:
 * <p/>
 * A[0] = 4
 * A[1] = 1
 * A[2] = 3
 * A[3] = 2
 * the function should return 1.
 * <p/>
 * Given array A such that:
 * <p/>
 * A[0] = 4
 * A[1] = 1
 * A[2] = 3
 * the function should return 0.
 * <p/>
 * Assume that:
 * <p/>
 * N is an integer within the range [1..100,000];
 * each element of array A is an integer within the range [1..1,000,000,000].
 * Complexity:
 * <p/>
 * expected worst-case time complexity is O(N);
 * expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
 * Elements of input arrays can be modified.
 * Created by Kacper on 2015-03-09.
 *
 * 50% - można poprawić walidację, bo na tym wyleciało
 */
public class PermCheck {

    public int solution(int[] A) {
        int solution = 0;
        if (A == null || A.length == 0) {
            return 1;
        }
        int max = 0;
        int sum = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] > max)
                max = A[i];
            sum += A[i];
        }

        int haveToBe = (((max + (max-A.length))*A.length)/2);
        if(sum == haveToBe)
            return 1;
        else
            return 0;
    }
}

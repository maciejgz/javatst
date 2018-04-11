package pl.mg.javatst.codility;

/**
 * A non-empty zero-indexed array A consisting of N integers is given.
 * <p>
 * A permutation is a sequence containing each element from 1 to N once,
 * and only once.
 * <p>
 * For example, array A such that:
 * <p>
 * A[0] = 4
 * A[1] = 1
 * A[2] = 3
 * A[3] = 2
 * is a permutation, but array A such that:
 * <p>
 * A[0] = 4
 * A[1] = 1
 * A[2] = 3
 * is not a permutation, because value 2 is missing.
 * <p>
 * The goal is to check whether array A is a permutation.
 * <p>
 * Write a function:
 * <p>
 * class Solution { public int solution(int[] A); }
 * <p>
 * that, given a zero-indexed array A, returns 1 if array A is a
 * permutation and 0 if it is not.
 * <p>
 * For example, given array A such that:
 * <p>
 * A[0] = 4
 * A[1] = 1
 * A[2] = 3
 * A[3] = 2
 * the function should return 1.
 * <p>
 * Given array A such that:
 * <p>
 * A[0] = 4
 * A[1] = 1
 * A[2] = 3
 * the function should return 0.
 * <p>
 * Assume that:
 * <p>
 * N is an integer within the range [1..100,000];
 * each element of array A is an integer within the range [1..1,000,000,000].
 * Complexity:
 * <p>
 * expected worst-case time complexity is O(N);
 * expected worst-case space complexity is O(N), beyond input storage
 * (not counting the storage required for input arguments).
 * Done
 * 75% - można poprawić walidację powtórzeń
 */
public class PermCheck {

    public static void main(String[] args) {
        PermCheck permCheckL2Done = new PermCheck();
        System.out.println(permCheckL2Done.solution(new int[]{4, 1, 3}));
    }

    public int solution(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        if (A.length == 1 && A[0] == 1) {
            return 1;
        } else if (A.length == 1) {
            return 0;
        }

        int isPermutation = 1;
        int sum = 0;
        int shouldBeSum = 0;
        for (int i = 0; i < A.length; i++) {
            sum += A[i];
            shouldBeSum += i + 1;
        }
        if (shouldBeSum != sum) {
            isPermutation = 0;
        }
        return isPermutation;
    }
}

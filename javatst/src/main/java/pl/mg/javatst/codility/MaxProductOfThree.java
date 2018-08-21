package pl.mg.javatst.codility;

import java.util.Arrays;

/**
 * A non-empty array A consisting of N integers is given. The product of triplet (P, Q, R) equates
 * to A[P] * A[Q] * A[R] (0 ≤ P < Q < R < N).
 *
 * <p>For example, array A such that:
 *
 * <p>A[0] = -3 A[1] = 1 A[2] = 2 A[3] = -2 A[4] = 5 A[5] = 6 contains the following example
 * triplets:
 *
 * <p>(0, 1, 2), product is −3 * 1 * 2 = −6 (1, 2, 4), product is 1 * 2 * 5 = 10 (2, 4, 5), product
 * is 2 * 5 * 6 = 60 Your goal is to find the maximal product of any triplet.
 *
 * <p>Write a function:
 *
 * <p>class Solution { public int solution(int[] A); }
 *
 * <p>that, given a non-empty array A, returns the value of the maximal product of any triplet.
 *
 * <p>For example, given array A such that:
 *
 * <p>A[0] = -3 A[1] = 1 A[2] = 2 A[3] = -2 A[4] = 5 A[5] = 6 the function should return 60, as the
 * product of triplet (2, 4, 5) is maximal.
 *
 * <p>Assume that:
 *
 * <p>N is an integer within the range [3..100,000]; each element of array A is an integer within
 * the range [−1,000..1,000]. Complexity:
 *
 * <p>expected worst-case time complexity is O(N*log(N)); expected worst-case space complexity is
 * O(1) (not counting the storage required for input arguments).
 *
 * 100%
 */
public class MaxProductOfThree {

  public static void main(String[] args) {

    int[] numbers = {-3, 1, 2, -2, 5, 6};
    MaxProductOfThree productOfThree = new MaxProductOfThree();
    System.out.println(productOfThree.solution(numbers));
  }

  public int solution(int[] A) {
    if (A == null) {
      return 0;
    }
    Arrays.sort(A);
    int result = 0;

    result = Math.max(A[0] * A[1] * A[A.length-1], A[A.length - 3] * A[A.length - 2] * A[A.length - 1]);

    return result;
  }
}

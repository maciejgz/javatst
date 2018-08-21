package pl.mg.javatst.codility;

import java.util.Arrays;

/**
 * An array A consisting of N integers is given. A triplet (P, Q, R) is triangular if 0 ≤ P < Q < R
 * < N and:
 *
 * <p>A[P] + A[Q] > A[R], A[Q] + A[R] > A[P], A[R] + A[P] > A[Q]. For example, consider array A such
 * that:
 *
 * <p>A[0] = 10 A[1] = 2 A[2] = 5 A[3] = 1 A[4] = 8 A[5] = 20 Triplet (0, 2, 4) is triangular.
 *
 * <p>Write a function:
 *
 * <p>class Solution { public int solution(int[] A); }
 *
 * <p>that, given an array A consisting of N integers, returns 1 if there exists a triangular
 * triplet for this array and returns 0 otherwise.
 *
 * <p>For example, given array A such that:
 *
 * <p>A[0] = 10 A[1] = 2 A[2] = 5 A[3] = 1 A[4] = 8 A[5] = 20 the function should return 1, as
 * explained above. Given array A such that:
 *
 * <p>A[0] = 10 A[1] = 50 A[2] = 5 A[3] = 1 the function should return 0.
 *
 * <p>Assume that:
 *
 * <p>N is an integer within the range [0..100,000]; each element of array A is an integer within
 * the range [−2,147,483,648..2,147,483,647]. Complexity:
 *
 * <p>expected worst-case time complexity is O(N*log(N)); expected worst-case space complexity is
 * O(N) (not counting the storage required for input arguments).
 *
 * <p>93 %
 */
public class Triangle {

  public int solution(int[] A) {
    int result = 0;
    Arrays.sort(A);

    if (A.length < 3) {
      return result;
    }

    for (int i = 0; i < A.length - 2; i++) {
      int P = i;
      int Q = i + 1;
      int R = i + 2;
      if ((A[P] + A[Q]) > A[R] && (A[Q] + A[R]) > A[P] && (A[R] + A[P]) > A[Q]) {
        result = 1;
        break;
      }
    }

    return result;
  }
}

package pl.mg.javatst.codility;

import java.util.Arrays;

/**
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

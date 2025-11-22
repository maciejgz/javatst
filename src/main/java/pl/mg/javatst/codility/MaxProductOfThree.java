package pl.mg.javatst.codility;

import java.util.Arrays;

/**
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

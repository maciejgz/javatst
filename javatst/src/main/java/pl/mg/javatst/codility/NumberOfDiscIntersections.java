package pl.mg.javatst.codility;

import java.util.Arrays;

/**
 *
 * 100%
 */
public class NumberOfDiscIntersections {

  public int solution(int[] A) {
    // write your code in Java SE 8
    // write your code in Java SE 8
    if (A.length < 2) return 0;
    Long r[] = new Long[A.length];
    Long l[] = new Long[A.length];

    for (int i = 0; i < A.length; i++) {
      if (A[i] == 0) {
        r[i] = l[i] = (long) i;
      } else {
        r[i] = (long) i + A[i];
        l[i] = (long) i - A[i];
      }
    }

    Arrays.sort(r);
    Arrays.sort(l);

    int in = 0;
    long s = 0;

    for (int i = 0; i < l.length; i++) {

      if (l[i] > r[in]) {
        int y = 0;
        while (in < r.length && l[i] > r[in]) {
          y++;
          in++;
        }
        if (y > 0) s = s + (l.length - i) * y;
      }
    }

    long t = (((long) A.length) * ((long) A.length - 1)) / 2;
    // System.out.println(t-s);
    if ((t - s) > 10000000) return -1;
    return (int) (t - s);
  }
}

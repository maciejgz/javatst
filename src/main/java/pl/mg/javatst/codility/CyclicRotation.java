package pl.mg.javatst.codility;

/**
 * https://app.codility.com/programmers/lessons/2-arrays/
 *
 * 100%
 *
 * @author Maciej Gzik
 */
public class CyclicRotation {

    public static void main(String[] args) {

    }


    public int[] solution(int[] A, int K) {
        int[] result = new int[A.length];

        for (int i = 0; i < A.length; i++) {
            result[(i + K) % A.length] = A[i];
        }

        return result;
    }
}

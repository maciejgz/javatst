package pl.mg.javatst.hackerrank;

/**
 * 25 points solution
 *
 */
public class ArrayManipulation {
    static long arrayManipulation(int n, int[][] queries) {
        long[] values = new long[n];
        int a = 0;
        int b = 0;
        int k = 0;
        long max = 0;

        for (int[] query : queries) {
            for (int i = (query[0] - 1); i <= query[1] - 1; i++) {
                values[i] += query[2];
                if (values[i] > max) {
                    max = values[i];
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {

    }
}

package pl.mg.javatst.codility;

/**
 * https://www.codewars.com/kata/561e9c843a2ef5a40c0000a4/train/java
 */
public class GapInPrimes {

    public static long[] gap(int g, long m, long n) {
        long[] result = new long[2];

        for (long j = m; j <= (n - g); j++) {
            if (checkIfPrime(j) && checkIfPrime(j + g)) {
                boolean isOk = true;
                for (long i = j + 1; i <= (j + g - 1); i++) {
                    if (checkIfPrime(i)) {
                        isOk = false;
                    }
                }
                if (isOk) {
                    result[0] = j;
                    result[1] = j + g;
                    break;
                }
            }
        }
        if (result[0] == 0) {
            result = null;
        }
        return result;
    }

    private static boolean checkIfPrime(long i) {
        if (i == 1) {
            return false;
        }
        for (long j = 2; j < (i / 2) + 1; j++) {
            if (i % j == 0) {
                return false;
            }
        }
        return true;
    }
}

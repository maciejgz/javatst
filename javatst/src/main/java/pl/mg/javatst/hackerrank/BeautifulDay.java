package pl.mg.javatst.hackerrank;

import java.util.Collections;
import java.util.List;

/**
 * https://www.hackerrank.com/challenges/beautiful-days-at-the-movies/problem
 */
public class BeautifulDay {

    public static void main(String[] args) {

    }


    static int beautifulDays(int i, int j, int k) {
        int result = 0;
        for (int l = i; l <= j; l++) {
            if (Math.abs( l -  reverse(l)) % k == 0) {
                result++;
            }
        }
        return result;
    }

    private static int reverse(int input) {
        return Integer.parseInt(new StringBuilder(String.valueOf(input)).reverse().toString());
    }
}

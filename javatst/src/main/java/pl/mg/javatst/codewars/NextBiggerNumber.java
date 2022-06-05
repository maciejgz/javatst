package pl.mg.javatst.codewars;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Create a function that takes a positive integer and returns the next bigger number that can be formed by rearranging its digits. For example:
 * <p>
 * 12 ==> 21
 * 513 ==> 531
 * 2017 ==> 2071
 * nextBigger(num: 12)   // returns 21
 * nextBigger(num: 513)  // returns 531
 * nextBigger(num: 2017) // returns 2071
 * If the digits can't be rearranged to form a bigger number, return -1 (or nil in Swift):
 * <p>
 * 9 ==> -1
 * 111 ==> -1
 * 531 ==> -1
 * nextBigger(num: 9)   // returns nil
 * nextBigger(num: 111) // returns nil
 * nextBigger(num: 531) // returns nil
 */
public class NextBiggerNumber {

    public static void main(String[] args) {
        System.out.println(nextBiggerNumber(12));
    }

    public static long nextBiggerNumber(long n) {
        if (n < 10) {
            return -1;
        }

        final StringBuilder sb = new StringBuilder();

        final String str = String.valueOf(n);
        for (int i = str.length() - 1; i > 0; i--) {
            // find the first place from the right where the left-digit is less than the right one
            final int digit = Character.getNumericValue(str.charAt(i - 1));
            if (digit < Character.getNumericValue(str.charAt(i))) {

                // find the smallest digit that is larger than left-digit
                int smallestLargerDigit = 9;
                int indexOfSmallestLargerDigit = 0;
                for (int j = i; j < str.length(); j++) {
                    final int actualDigit = Character.getNumericValue(str.charAt(j));
                    if (actualDigit > digit && actualDigit <= smallestLargerDigit) {
                        smallestLargerDigit = actualDigit;
                        indexOfSmallestLargerDigit = j;
                    }
                }

                // collect the righter digits except the smallest larger one
                final ArrayList<Integer> digits = new ArrayList<>();
                for (int j = i - 1; j < str.length(); j++) {
                    if (j != indexOfSmallestLargerDigit) {
                        digits.add(Character.getNumericValue(str.charAt(j)));
                    }
                }

                sb.append(str, 0, i - 1);
                sb.append(smallestLargerDigit);

                // sort the digits
                Collections.sort(digits);
                for (int actualDigit : digits) {
                    sb.append(actualDigit);
                }
                return Long.parseLong(sb.toString());
            }
        }
        return -1;
    }
}

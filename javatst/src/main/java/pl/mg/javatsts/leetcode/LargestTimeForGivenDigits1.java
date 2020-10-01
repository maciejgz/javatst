package pl.mg.javatsts.leetcode;

import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Given an array arr of 4 digits, find the latest 24-hour time that can be made using each digit exactly once.
 * <p>
 * 24-hour times are formatted as "HH:MM", where HH is between 00 and 23, and MM is between 00 and 59. The earliest 24-hour time is 00:00, and the latest is 23:59.
 * <p>
 * Return the latest 24-hour time in "HH:MM" format.  If no valid time can be made, return an empty string.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: A = [1,2,3,4]
 * Output: "23:41"
 * Explanation: The valid 24-hour times are "12:34", "12:43", "13:24", "13:42", "14:23", "14:32", "21:34", "21:43", "23:14", and "23:41". Of these times, "23:41" is the latest.
 */
@Data
public class LargestTimeForGivenDigits1 {

    public static void main(String[] args) {
        int[] test = {2, 0, 0, 4};
        LargestTimeForGivenDigits1 digits = new LargestTimeForGivenDigits1();
        System.out.println(digits.largestTimeFromDigits(test));

    }

    public String largestTimeFromDigits(int[] arr) {
        List<Integer> result = new ArrayList<>();
        printAllRecursive(arr.length, arr, result);
        result.sort(Integer::compareTo);
        if (result.size() == 0) {
            return "";
        }
        int max = result.get(result.size() - 1);

        BigDecimal decimal = BigDecimal.valueOf((double) max / 100);

        String mainValue = String.valueOf(max / 100);
        if (mainValue.length() == 1) {
            mainValue = "0" + mainValue;
        }


        String doubleAsString = String.valueOf(decimal);
        int indexOfDecimal = doubleAsString.indexOf(".");
        String decimalString = doubleAsString.substring(indexOfDecimal + 1);
        if (decimalString.length() == 1) {
            decimalString = decimalString + "0";
        }

        return mainValue + ":" + decimalString;
    }

    private static void printAllRecursive(
            int n, int[] elements, List<Integer> result) {

        if (n == 1) {
            Optional<Integer> toAdd = saveCombination(elements);
            toAdd.ifPresent(result::add);
        } else {
            for (int i = 0; i < n - 1; i++) {
                printAllRecursive(n - 1, elements, result);
                if (n % 2 == 0) {
                    swap(elements, i, n - 1);
                } else {
                    swap(elements, 0, n - 1);
                }
            }
            printAllRecursive(n - 1, elements, result);
        }
    }

    private static void swap(int[] input, int a, int b) {
        int tmp = input[a];
        input[a] = input[b];
        input[b] = tmp;
    }

    private static Optional<Integer> saveCombination(int[] input) {
        if (isCorrectHour(input)) {
            return Optional.of(toInt(input));
        } else {
            return Optional.empty();
        }
    }

    private static int toInt(int[] input) {
        return input[0] * 1000 + input[1] * 100 + input[2] * 10 + input[3];
    }

    private static boolean isCorrectHour(int[] input) {
        int hour = input[0] * 10 + input[1];
        int minute = input[2] * 10 + input[3];

        return hour >= 0 && hour <= 23 && minute >= 0 && minute <= 59;
    }


}

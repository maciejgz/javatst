package pl.mg.javatst.hackerrank;

import java.util.Arrays;

/**
 * https://www.hackerrank.com/challenges/mark-and-toys/
 * 35 points (max)
 */
public class MarkAndToys {

    static int maximumToys(int[] prices, int k) {
        Arrays.sort(prices);
        int numOfToys = 0;
        for (int price : prices) {
            if (k > price) {
                numOfToys++;
                k -= price;
            }
        }
        return numOfToys;
    }
}

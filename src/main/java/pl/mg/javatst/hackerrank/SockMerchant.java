package pl.mg.javatst.hackerrank;

import java.util.HashMap;
import java.util.Map;

/**
 * John works at a clothing store. He has a large pile of socks that he must pair by color for sale. Given an array of
 * integers representing the color of each sock, determine how many pairs of socks with matching colors there are.
 * <p>
 * For example, there are  socks with colors . There is one pair of color  and one of color . There are three odd socks
 * left, one of each color. The number of pairs is .
 * <p>
 * Function Description
 * <p>
 * Complete the sockMerchant function in the editor below. It must return an integer representing the number of matching
 * pairs of socks that are available.
 * <p>
 * sockMerchant has the following parameter(s):
 * <p>
 * n: the number of socks in the pile
 * ar: the colors of each sock
 * Input Format
 * <p>
 * The first line contains an integer , the number of socks represented in .
 * The second line contains  space-separated integers describing the colors  of the socks in the pile.
 * <p>
 * Constraints
 * <p>
 * where
 * Output Format
 * <p>
 * Return the total number of matching pairs of socks that John can sell.
 * <p>
 * Sample Input
 * <p>
 * 9
 * 10 20 20 10 10 30 50 10 20
 * Sample Output
 * <p>
 * 3
 */
public class SockMerchant {

    static int sockMerchant(int n, int[] ar) {
        Map<Integer, Integer> result = new HashMap<>();

        for (int i : ar) {
            if (!result.containsKey(i)) {
                result.put(i, 1);
            } else {
                result.put(i, result.get(i) + 1);
            }
        }

        int res = 0;
        for (Integer integer : result.keySet()) {
            res += Math.floor(result.get(integer) / 2);
        }
        return res;
    }
}

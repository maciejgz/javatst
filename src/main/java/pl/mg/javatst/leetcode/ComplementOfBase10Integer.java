package pl.mg.javatst.leetcode;

/**
 * https://leetcode.com/explore/challenge/card/october-leetcoding-challenge/559/week-1-october-1st-october-7th/3484/
 * Every non-negative integer N has a binary representation.  For example, 5 can be represented as "101" in binary, 11
 * as "1011" in binary, and so on.  Note that except for N = 0, there are no leading zeroes in any binary representation.
 * <p>
 * The complement of a binary representation is the number in binary you get when changing every 1 to a 0 and 0 to a 1.
 * For example, the complement of "101" in binary is "010" in binary.
 * <p>
 * For a given number N in base-10, return the complement of it's binary representation as a base-10 integer.
 */
public class ComplementOfBase10Integer {

    public int bitwiseComplement(int N) {
        String s = Integer.toBinaryString(N);
        StringBuilder result = new StringBuilder();
        for (char c : s.toCharArray()) {
            result.append(c == '1' ? '0' : '1');
        }
        return Integer.parseInt(result.toString(), 2);
    }
}

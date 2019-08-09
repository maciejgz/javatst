package pl.mg.javatst.codility;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * A string S consisting of N characters is considered to be properly nested if any of the following conditions is true:
 * <p>
 * S is empty;
 * S has the form "(U)" or "[U]" or "{U}" where U is a properly nested string;
 * S has the form "VW" where V and W are properly nested strings.
 * For example, the string "{[()()]}" is properly nested but "([)()]" is not.
 * <p>
 * Write a function:
 * <p>
 * class Solution { public int solution(String S); }
 * <p>
 * that, given a string S consisting of N characters, returns 1 if S is properly nested and 0 otherwise.
 * <p>
 * For example, given S = "{[()()]}", the function should return 1 and given S = "([)()]", the function should return 0, as explained above.
 * <p>
 * Write an efficient algorithm for the following assumptions:
 * <p>
 * N is an integer within the range [0..200,000];
 * string S consists only of the following characters: "(", "{", "[", "]", "}" and/or ")".
 * Copyright 2009–2019 by Codility Limited. All Rights Reserved. Unauthorized copying, publication or disclosure
 *
 * 35% solution
 *
 */
public class Brackets {
    public static void main(String[] args) {
    }

    public int solution(String S) {
        if (S == null || S.length() == 0) {
            return 1;
        }
        if (S.length() % 2 != 0) {
            return 0;
        }
        char[] chars = S.toCharArray();
        Stack<Character> stack = new Stack<>();
        List<Character> characters = new ArrayList<>();
        for (char aChar : chars) {
            if (aChar == '{' || aChar == '[' || aChar == '(') {
                stack.push(aChar);
            } else if (aChar == '}' || aChar == ']' || aChar == ')') {
                Character pop = stack.pop();
                if ((pop == '{' && aChar != '}') || (pop == '[' && aChar != ']') || (pop == '(' && aChar != ')')) {
                    return 0;
                }
            } else {
                return 0;
            }
        }
        return 1;
    }
}

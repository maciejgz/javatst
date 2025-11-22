package pl.mg.javatst.codility;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
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

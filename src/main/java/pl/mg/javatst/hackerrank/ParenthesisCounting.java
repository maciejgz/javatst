package pl.mg.javatst.hackerrank;

public class ParenthesisCounting {

    public static void main(String[] args) {
        String input = "((()()))";
        System.out.println(isCorrect(input));
        System.out.println(isCorrect("(()"));
        System.out.println(isCorrect("(()))"));
        System.out.println(isCorrect(""));
    }

    static boolean isCorrect(String input) {
        char[] chars = input.toCharArray();
        boolean result = true;
        int parents = 0;
        for (char aChar : chars) {
            if (aChar == '(') {
                parents++;
            } else if (aChar == ')') {
                parents--;
            }
            if (parents < 0) {
                result = false;
                break;
            }
        }

        if (parents > 0) {
            result = false;
        }
        return result;
    }
}

package pl.mg.javatst.codewars;

public class SquareDigit {

    public static void main(String[] args) {

        squareDigits(9119);
    }

    public static int squareDigits(int n) {
        char[] chars = Integer.toString(n).toCharArray();
        String concat = "";
        for (char aChar : chars) {
            concat += Integer.parseInt(String.valueOf(aChar)) * Integer.parseInt(String.valueOf(aChar));
        }

        return Integer.valueOf(concat);
    }
}

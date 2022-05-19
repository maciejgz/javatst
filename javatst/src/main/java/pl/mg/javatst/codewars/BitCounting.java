package pl.mg.javatst.codewars;

public class BitCounting {

    public static void main(String[] args) {
        countBits(1);
    }

    public static int countBits(int n) {
        String s = Integer.toBinaryString(n);
        int counter = 0;
        for (char character : s.toCharArray()) {
            if (character == '1') {
                counter++;
            }
        }
        return counter;
    }
}

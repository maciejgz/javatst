package pl.mg.javatst.codewars;

public class DigPow {

    public static void main(String[] args) {
        System.out.println(digPow(89, 1));
    }

    public static long digPow(int n, int p) {
        int result = -1;
        String nString = String.valueOf(n);
        int value = 0;
        for (int i = 0; i < nString.length(); i++) {
            value += Math.pow(Integer.valueOf(String.valueOf(nString.charAt(i))), p);
            p++;
        }

        if (value % n == 0) {
            result = value / n;
        }
        return result;
    }
}

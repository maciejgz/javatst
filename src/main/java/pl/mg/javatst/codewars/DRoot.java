package pl.mg.javatst.codewars;

public class DRoot {

    public static void main(String[] args) {
        System.out.println(digital_root(456));
    }


    public static int digital_root(int n) {
        int result = 0;
        if (n < 0) {
            return 0;
        }

        while (n != 0) {
            result += n % 10;
            n = (int) Math.floor(n / 10);
        }

        if (result >= 10) {
            result = digital_root(result);
        }

        return result;
    }
}

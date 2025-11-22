package pl.mg.javatst.codewars;

public class Persist {

    public static void main(String[] args) {
        System.out.println(persistence(39));
    }


    public static int persistence(long n) {
        int counter = 0;
        while (n >= 10) {
            String sValue = String.valueOf(n);
            n = 1;
            for (int i = 0; i < sValue.length(); i++) {
                n *= Integer.valueOf(String.valueOf(sValue.charAt(i)));
            }
            counter++;
        }

        return counter;
    }
}

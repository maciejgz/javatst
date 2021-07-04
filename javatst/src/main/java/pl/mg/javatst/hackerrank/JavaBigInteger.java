package pl.mg.javatst.hackerrank;

import java.util.ArrayList;
import java.util.Scanner;

public class JavaBigInteger {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println((char) '2');
        String firstValue = scanner.nextLine();
        String secondValue = scanner.nextLine();
        System.out.println(add(firstValue, secondValue));
    }

    static String add(String a, String b) {

        StringBuilder b1 = new StringBuilder();
        b1.append(a);
        String first = b1.reverse().toString();

        StringBuilder b2 = new StringBuilder();
        b2.append(b);
        String second = b2.reverse().toString();

        ArrayList<Character> result = new ArrayList<>();
        int add = 0;
        for (int i = 0; i < first.length(); i++) {
            char secVal = '0';
            if (second.length() > i) {
                secVal = second.charAt(i);
            }
            int res = ((int) a.charAt(i) + (int) secVal) + add;
            result.add((char) (res % 10));
            if (res > 9) {
                add = 1;
            } else {
                add = 0;
            }

        }
        StringBuilder build = new StringBuilder();
        for (Character character : result) {
            build.append(character);
        }
        return build.reverse().toString();
    }
}

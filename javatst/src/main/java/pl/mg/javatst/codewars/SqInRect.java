package pl.mg.javatst.codewars;

import java.util.ArrayList;
import java.util.List;

public class SqInRect {

    public static void main(String[] args) {
        List<Integer> integers = sqInRect(10600, 3);
        integers.stream().forEach(System.out::println);
        System.out.println("test");
    }

    public static List<Integer> sqInRect(int lng, int wdth) {
        if (lng == wdth) {
            return null;
        }

        List<Integer> squares = new ArrayList<>();
        while (true) {
            if (lng == 1 || wdth == 1) {
                int max = Math.max(lng, wdth);
                for (int i = 0; i < max; i++) {
                    squares.add(1);
                }
                break;
            }
            int max = Math.max(lng, wdth);
            if (max == lng) {
                squares.add(wdth);
                lng = lng - wdth;
            } else {
                squares.add(lng);
                wdth = wdth - lng;
            }
        }
        return squares;
    }
}

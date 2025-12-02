package pl.mg.javatst.adventofcode2025;

import pl.mg.javatst.adventofcode2024.Utils;

import java.util.List;

public class Day02GiftShop {

    static void main() {
        System.out.println("Day 02 - Gift Shop");
        List<String> lines = Utils.readLines("src/main/resources/adventofcode2025/day02_input.txt");


        // split by ,
        long sum = 0L;
        String[] ranges = lines.getFirst().split(",");
        for (String range : ranges) {
            String start = range.trim().split("-")[0];
            String end = range.trim().split("-")[1];

            long startInt = Long.parseLong(start);
            long endInt = Long.parseLong(end);

            for (long i = startInt; i <= endInt; i++) {
                if (isGift(i)) {
                    sum += i;
                }
            }

        }

        System.out.println("sum = " + sum);

    }

    private static boolean isGift(long i) {
        String number = String.valueOf(i);

        if (number.length() % 2 == 0) {
            return number.substring(0, number.length() / 2).equals(number.substring(number.length() / 2));
        }
        return false;
    }
}

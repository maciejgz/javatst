package pl.mg.javatst.adventofcode2024;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Da11PlutonianPebbles {

    public static void main(String[] args) {
        System.out.println("Day 11 Plutonian Pebbles");
        String input = "572556 22 0 528 4679021 1 10725 2790";
        System.out.println("Simulation result: " + simulate(input));
    }

    private static long simulate(String input) {
        List<Long> pebbles = Stream.of(input.split(" ")).map(Long::parseLong).toList();

        for (int iteration = 0; iteration < 25; iteration++) {
            List<Long> transformedPebbles = new ArrayList<>();

            for (Long pebble : pebbles) {
                if (pebble == 0) {
                    transformedPebbles.add(1L);
                } else {
                    String pebbleStr = pebble.toString();
                    if (pebbleStr.length() % 2 == 0) {
                        int mid = pebbleStr.length() / 2;
                        transformedPebbles.add(Long.parseLong(pebbleStr.substring(0, mid)));
                        transformedPebbles.add(Long.parseLong(pebbleStr.substring(mid)));
                    } else {
                        transformedPebbles.add(pebble * 2024);
                    }
                }
            }

            pebbles = transformedPebbles;
        }

        return pebbles.size();
    }
}

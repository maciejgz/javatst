package pl.mg.javatst.adventofcode;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Lanternfish6 {

    public static void main(String[] args) {
        try {
            List<String> input = FileUtil.readLines("adventofcode//6.txt");
            int result = simulate80Days(input);
            System.out.println("after 80 days: " + result);
            long result256 = simulate256Days(input);
            System.out.println("after 256 days: " + result256);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int simulate80Days(List<String> input) {
        List<Lanternfish> fishes = Arrays.stream(input.get(0).split(","))
                .map(state -> new Lanternfish(Integer.parseInt(state))).collect(Collectors.toList());
        System.out.println(fishes.size());
        for (int i = 0; i < 80; i++) {
            int newFishes = 0;
            for (int j = 0; j < fishes.size(); j++) {
                if (fishes.get(j).processDay()) {
                    newFishes++;
                }
            }
            if (i != 79) {
                for (int j = 0; j < newFishes; j++) {
                    fishes.add(new Lanternfish(9));
                }
            }
        }
        return fishes.size();
    }

    private static long simulate256Days(List<String> input) {
        List<Lanternfish> fishes = Arrays.stream(input.get(0).split(","))
                .map(state -> new Lanternfish(Integer.parseInt(state))).collect(Collectors.toList());
        int result = 0;
        long[] fishesGrouped = new long[9];
        for (Lanternfish fish : fishes) {
            fishesGrouped[fish.state]++;
        }

        for (int i = 0; i < 256; i++) {
            long temp = fishesGrouped[0];
            fishesGrouped[0] = fishesGrouped[1];
            fishesGrouped[1] = fishesGrouped[2];
            fishesGrouped[2] = fishesGrouped[3];
            fishesGrouped[3] = fishesGrouped[4];
            fishesGrouped[4] = fishesGrouped[5];
            fishesGrouped[5] = fishesGrouped[6];
            fishesGrouped[6] = fishesGrouped[7] + temp;
            fishesGrouped[7] = fishesGrouped[8];

            if (i != 0) {
                fishesGrouped[8] = temp;
            }
        }

        return fishesGrouped[0] +
                fishesGrouped[1] +
                fishesGrouped[2] +
                fishesGrouped[3] +
                fishesGrouped[4] +
                fishesGrouped[5] +
                fishesGrouped[6] +
                fishesGrouped[7] +
                fishesGrouped[8];
    }

    private static class Lanternfish {
        public int state;

        public Lanternfish(int state) {
            this.state = state;
        }

        public boolean processDay() {
            this.state--;
            if (state == 0) {
                state = 7;
                return true;
            } else {
                return false;
            }
        }
    }
}

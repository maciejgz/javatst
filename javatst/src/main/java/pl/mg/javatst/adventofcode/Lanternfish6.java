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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int simulate80Days(List<String> input) {
        List<Lanternfish> fishes = Arrays.stream(input.get(0).split(","))
                .map(state -> new Lanternfish(Integer.parseInt(state))).collect(Collectors.toList());
        System.out.println(fishes.size());
        for (int i = 0; i < 18; i++) {
            for (int j = 0; j < fishes.size(); j++) {
                if (fishes.get(j).processDay()) {
                    fishes.add(new Lanternfish(8));
                }
            }
        }
        return fishes.size();
    }

    private static class Lanternfish {
        public int state;


        public Lanternfish(int state) {
            this.state = state;
        }

        public boolean processDay() {
            this.state--;
            if (state == 0) {
                state = 6;
                return true;
            } else {
                return false;
            }
        }
    }
}

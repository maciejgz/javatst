package pl.mg.javatst.adventofcode;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Whales7 {

    public static void main(String[] args) {

        try {
            List<String> input = FileUtil.readLines("adventofcode//7.txt");
            int fuel = centerHorizontally(input);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static int centerHorizontally(List<String> input) {
        List<Integer> positions = Arrays.stream(input.get(0).split(",")).map(Integer::parseInt).collect(Collectors.toList());

        Optional<Integer> maxOpt = positions.stream().max(Integer::compareTo);
        int max = 0;
        if (maxOpt.isPresent()) {
            max = maxOpt.get();
        }



        return 0;
    }
}

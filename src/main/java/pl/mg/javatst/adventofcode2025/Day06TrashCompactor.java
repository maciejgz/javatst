package pl.mg.javatst.adventofcode2025;

import lombok.extern.slf4j.Slf4j;
import pl.mg.javatst.adventofcode2024.Utils;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Day06TrashCompactor {

    static void main() {
        List<String> lines = Utils.readLines("src/main/resources/adventofcode2025/day06_input.txt");
        List<List<Integer>> numbers = new ArrayList<>();
        List<String> operators = new ArrayList<>();
        long sum = 0;
        for (int i = 0; i < lines.size(); i++) {
            String[] split = lines.get(i).trim().split("\\s+");
            if (i != lines.size() - 1) {
                for (int j = 0; j < split.length; j++) {
                    if (numbers.size() <= j) {
                        numbers.add(new ArrayList<>());
                    }
                    numbers.get(j).add(Integer.parseInt(split[j]));
                }
            } else {
                operators = List.of(split);
            }
        }

        for (int i = 0; i < operators.size(); i++) {
            String operator = operators.get(i);
            if (operator.equals("+")) {
                int result = 0;
                for (Integer number : numbers.get(i)) {
                    result += number;
                }
                sum += result;
            } else if (operator.equals("*")) {
                long result = 1;
                for (Integer number : numbers.get(i)) {
                    result = number * result;
                }
                sum += result;
            }
        }

        log.debug("sum: {}", sum);

    }
}

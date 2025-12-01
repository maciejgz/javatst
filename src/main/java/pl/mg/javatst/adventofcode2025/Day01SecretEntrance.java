package pl.mg.javatst.adventofcode2025;

import lombok.extern.slf4j.Slf4j;
import pl.mg.javatst.adventofcode2024.Utils;

import java.util.List;

@Slf4j
public class Day01SecretEntrance {

    static void main() {
        List<String> lines = Utils.readLines("src/main/resources/adventofcode2025/day01_input.txt");

        int position = 50;

        int counter = 0;
        int change;
        for (String line : lines) {
            if (line.startsWith("L")) {
                change = -(Integer.parseInt(line.substring(1)) % 100);
            } else {
                change = Integer.parseInt(line.substring(1)) % 100;
            }

            if ((change < 0) && ((position + change) < 0)) {
                position = 100 + (position + change);
            } else if ((change > 0) && ((position + change) > 99)) {
                position = change + position - 100;
            } else {
                position += change;
            }

            log.debug("position {}, change {}", position, change);

            if (position == 0) {
                counter++;
            }
        }
        log.debug("result: {}", counter);
    }
}

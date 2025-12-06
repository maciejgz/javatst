package pl.mg.javatst.adventofcode2025;

import lombok.extern.slf4j.Slf4j;
import pl.mg.javatst.adventofcode2024.Utils;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Day05Cafeteria {


    static void main() {
        log.info("Day 05 - Cafeteria");

        List<String> lines = Utils.readLines("src/main/resources/adventofcode2025/day05_input.txt");

        List<Range> ranges = new ArrayList<>();
        List<Long> ingredients = new ArrayList<>();
        for (String line : lines) {
            if (line.split("-").length == 2) {
                ranges.add(new Range(Long.parseLong(line.split("-")[0]), Long.parseLong(line.split("-")[1])));
            } else if (line.isEmpty()) {
                continue;
            } else if (line.split("-").length == 1) {
                ingredients.add(Long.parseLong(line.trim()));
            }
        }

        int spoiled = 0;
        for (Long ingredient : ingredients) {
            for (Range range : ranges) {
                if (ingredient >= range.start && ingredient <= range.stop) {
                    spoiled++;
                    break;
                }
            }
        }

        System.out.println("spoiled: " + spoiled);
    }

    private record Range(long start, long stop) {
    }
}

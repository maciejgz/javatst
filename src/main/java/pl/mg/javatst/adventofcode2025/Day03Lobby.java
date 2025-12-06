package pl.mg.javatst.adventofcode2025;

import lombok.extern.slf4j.Slf4j;
import pl.mg.javatst.adventofcode2024.Utils;

import java.util.List;

@Slf4j
public class Day03Lobby {

    static void main() {
        List<String> lines = Utils.readLines("src/main/resources/adventofcode2025/day03_input.txt");
        long sum = 0;

        for (String line : lines) {
            List<Integer> batteries = line.chars().map(c -> c - '0').boxed().toList();
            int maxJoltage = findMaxJoltage(batteries);
            sum += maxJoltage;
            log.debug("For line {} result: {}", line, maxJoltage);
        }

        System.out.println(sum);
    }

    /**
     * Znajduje największą możliwą liczbę dwucyfrową, którą można utworzyć
     * wybierając dwie cyfry z listy, zachowując ich oryginalną kolejność.
     * Pierwsza wybrana cyfra będzie cyfrą dziesiątek, druga - cyfrą jedności.
     */
    private static int findMaxJoltage(List<Integer> batteries) {
        int maxJoltage = 0;

        // Dla każdej pary (i, j) gdzie i < j, sprawdzamy joltage = batteries[i] * 10 + batteries[j]
        for (int i = 0; i < batteries.size() - 1; i++) {
            for (int j = i + 1; j < batteries.size(); j++) {
                int joltage = batteries.get(i) * 10 + batteries.get(j);
                if (joltage > maxJoltage) {
                    maxJoltage = joltage;
                }
            }
        }

        return maxJoltage;
    }

}

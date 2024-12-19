package pl.mg.javatst.adventofcode2024;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class Day10HoofIt {

    public static void main(String[] args) {
        log.debug("Day 10 Hoof it");
        List<String> lines = Utils.readLines("src/main/resources/adventofcode/day10_input.txt");
        char[][] input = lines.stream().map(String::toCharArray).toArray(char[][]::new);
        int result = 0;
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[i].length; j++) {
                if (input[i][j] == '0') {
                    log.debug("checking element: i-{}, j-{} value={}", i, j, input[i][j]);
                    boolean[][] visited = new boolean[input.length][input[i].length];
                    result += solution(i, j, input, visited, 0, 0);
                }
            }
        }
        log.debug("Result: {}", result);
    }

    public static int solution(int i, int j, char[][] input, boolean[][] visited, int currentValue, int counter) {
        // Check finish condition
        if (currentValue == 9) {
            return counter + 1;
        }
        // Mark the current cell as visited
        visited[i][j] = true;
        // Check surroundings if they are not visited yet and are current_value + 1
        if (i - 1 >= 0 && !visited[i - 1][j] && input[i - 1][j] == currentValue + 1) {
            counter = solution(i - 1, j, input, visited, currentValue + 1, counter);
        }
        if (i + 1 < input.length && !visited[i + 1][j] && input[i + 1][j] == currentValue + 1) {
            counter = solution(i + 1, j, input, visited, currentValue + 1, counter);
        }
        if (j - 1 >= 0 && !visited[i][j - 1] && input[i][j - 1] == currentValue + 1) {
            counter = solution(i, j - 1, input, visited, currentValue + 1, counter);
        }
        if (j + 1 < input[i].length && !visited[i][j + 1] && input[i][j + 1] == currentValue + 1) {
            counter = solution(i, j + 1, input, visited, currentValue + 1, counter);
        }
        // Unmark the current cell before returning
        visited[i][j] = false;
        return counter;
    }


}

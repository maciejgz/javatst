package pl.mg.javatst.adventofcode2024;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Slf4j
public class Day10HoofIt {

    public static void main(String[] args) {
        log.debug("Day 10 Hoof it");
        List<String> lines = Utils.readLines("src/main/resources/adventofcode/day10_input.txt");
        char[][] input = lines.stream().map(String::toCharArray).toArray(char[][]::new);
/*        int result = 0;
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[i].length; j++) {
                if (input[i][j] == '0') {
                    log.debug("checking element: i-{}, j-{} value={}", i, j, input[i][j]);
                    boolean[][] visited = new boolean[input.length][input[i].length];
                    result += solution(i, j, input, visited, 0, 0);
                }
            }
        }*/

        log.debug("Total trailhead scores: " + calculateTrailheadScores(input));
    }

    public static int calculateTrailheadScores(char[][] map) {
        int totalScore = 0;

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == '0') {
                    totalScore += calculateScoreFromTrailhead(map, i, j);
                }
            }
        }

        return totalScore;
    }

    public static int calculateScoreFromTrailhead(char[][] map, int startX, int startY) {
        int score = 0;
        int rows = map.length;
        int cols = map[0].length;
        boolean[][] visited = new boolean[rows][cols];

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{startX, startY, 0}); // {x, y, currentHeight}
        visited[startX][startY] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];
            int height = current[2];

            if (height == 9) {
                score++;
                continue;
            }

            // Check all 4 directions
            int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
            for (int[] dir : directions) {
                int newX = x + dir[0];
                int newY = y + dir[1];

                if (isValid(map, visited, newX, newY, height + 1)) {
                    visited[newX][newY] = true;
                    queue.add(new int[]{newX, newY, height + 1});
                }
            }
        }

        return score;
    }

    public static boolean isValid(char[][] map, boolean[][] visited, int x, int y, int expectedHeight) {
        return x >= 0 && x < map.length && y >= 0 && y < map[0].length &&
                !visited[x][y] && map[x][y] - '0' == expectedHeight;
    }

}

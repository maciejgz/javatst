package pl.mg.javatst.adventofcode2025;

import pl.mg.javatst.adventofcode2024.Utils;

import java.util.List;

public class Day04PrintingDepartment {

    static void main() {
        List<String> lines = Utils.readLines("src/main/resources/adventofcode2025/day04_input.txt");
        int height = lines.size();
        int width = lines.get(0).length();
        boolean[][] rolls = new boolean[height][width];

        // Parse the input - @ means roll of paper
        for (int row = 0; row < height; row++) {
            String line = lines.get(row);
            for (int col = 0; col < width; col++) {
                rolls[row][col] = line.charAt(col) == '@';
            }
        }

        // Count accessible rolls (those with less than 4 adjacent rolls)
        int accessibleCount = 0;
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                if (rolls[row][col]) {
                    int adjacentCount = countAdjacentRolls(rolls, row, col, height, width);
                    if (adjacentCount < 4) {
                        accessibleCount++;
                    }
                }
            }
        }

        System.out.println("Accessible rolls: " + accessibleCount);
    }

    private static int countAdjacentRolls(boolean[][] rolls, int row, int col, int height, int width) {
        int count = 0;
        // Check all 8 directions
        int[][] directions = {
                {-1, -1}, {-1, 0}, {-1, 1},  // top-left, top, top-right
                {0, -1},           {0, 1},    // left, right
                {1, -1},  {1, 0},  {1, 1}     // bottom-left, bottom, bottom-right
        };

        for (int[] dir : directions) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];
            if (newRow >= 0 && newRow < height && newCol >= 0 && newCol < width) {
                if (rolls[newRow][newCol]) {
                    count++;
                }
            }
        }

        return count;
    }
}

package pl.mg.javatst.adventofcode2025;

import lombok.extern.slf4j.Slf4j;
import pl.mg.javatst.adventofcode2024.Utils;

import java.util.List;

/**
 * Advent of code 2025 day 9
 *
 * @see <a href="https://adventofcode.com/2025/day/9">Advent of code 2025 day 9</a>
 */
@Slf4j
public class Day09MovieTheater {


    static void main() {
        log.debug("Day09MovieTheater main()");

        List<String> lines = Utils.readLines("src/main/resources/adventofcode2025/day09_input.txt");

        // Parse all red tile coordinates
        List<int[]> coords = new java.util.ArrayList<>();
        for (String line : lines) {
            if (line == null || line.trim().isEmpty()) continue;
            String[] p = line.trim().split(",");
            int x = Integer.parseInt(p[0].trim());
            int y = Integer.parseInt(p[1].trim());
            coords.add(new int[]{x, y});
        }

        log.debug("Total red tiles: {}", coords.size());

        // Find the largest rectangle area using any two red tiles as opposite corners
        long maxArea = 0;
        int[] bestTile1 = null;
        int[] bestTile2 = null;

        for (int i = 0; i < coords.size(); i++) {
            for (int j = i + 1; j < coords.size(); j++) {
                int[] tile1 = coords.get(i);
                int[] tile2 = coords.get(j);

                // Calculate the area of the rectangle formed by these two tiles as opposite corners
                // The rectangle includes all tiles from one corner to the other (inclusive)
                long width = Math.abs(tile2[0] - tile1[0]) + 1L;
                long height = Math.abs(tile2[1] - tile1[1]) + 1L;
                long area = width * height;

                if (area > maxArea) {
                    maxArea = area;
                    bestTile1 = tile1;
                    bestTile2 = tile2;
                }
            }
        }

        log.debug("Largest rectangle area: {}", maxArea);
        if (bestTile1 != null) {
            log.debug("Best corners: ({},{}) and ({},{})", bestTile1[0], bestTile1[1], bestTile2[0], bestTile2[1]);
            log.debug("Width: {}, Height: {}", Math.abs(bestTile2[0] - bestTile1[0]) + 1, Math.abs(bestTile2[1] - bestTile1[1]) + 1);
        }
    }

}

package pl.mg.javatst.adventofcode;

import com.drew.lang.annotations.NotNull;
import ucar.coord.Coordinate;

import java.awt.geom.Line2D;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class HydrothermalVenture5 {

    public static void main(String[] args) {
        try {
            List<String> input = FileUtil.readLines("adventofcode//5.txt");
            int dangerousAreas = findDangerousAreas(input);
            System.out.println("dangerous: " + dangerousAreas);
            int diagonal = part2(input);
            System.out.println("diagonal: " + diagonal);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int findDangerousAreasWithDiagonal(List<String> input) {
        int[][] board = new int[findTopX(input) + 1][findTopY(input) + 1];
        for (String in : input) {
            int x1 = Integer.parseInt(in.split(" -> ")[0].split(",")[0].trim());
            int y1 = Integer.parseInt(in.split(" -> ")[0].split(",")[1].trim());

            int x2 = Integer.parseInt(in.split(" -> ")[1].split(",")[0].trim());
            int y2 = Integer.parseInt(in.split(" -> ")[1].split(",")[1].trim());

            if (x1 > x2) {
                int temp = x2;
                x2 = x1;
                x1 = temp;

                int tempY = y2;
                y2 = y1;
                y1 = tempY;
            }

            if (!isHorizontalOrVertical(x1, y1, x2, y2) && !isDiagonal(x1, y1, x2, y2)) {
                continue;
            }
            for (int i = x1; i <= x2; i++) {
                if (y1 <= y2) {
                    for (int j = y1; j <= y2; j++) {
                        board[j][i]++;
                    }
                } else {
                    for (int j = y2; j <= y1; j++) {
                        board[j][i]++;
                    }
                }
            }
        }

        int result = 0;
        for (int[] row : board) {
            for (int cell : row) {
                if (cell > 1) {
                    result++;
                }
            }
        }
        return result;
    }


    private static boolean isDiagonal(int x1, int y1, int x2, int y2) {
        return Math.abs(x2 - x1) == Math.abs(y2 - y1);
    }

    private static Integer part2(List<String> input) {
        var lines = input;

        return countOverlappingVents(lines, true);
    }

    private static final int GRID_WIDTH = 1000;
    private static final int GRID_HEIGHT = 1000;
    private static final Pattern SPLIT_PATTERN = Pattern.compile(",| -> ");

    private static int countOverlappingVents(@NotNull List<String> lines, boolean includeDiagonals) {
        var grid = new int[GRID_WIDTH * GRID_HEIGHT];
        var gridCount = 0;

        for (var line : lines) {
            var nums = SPLIT_PATTERN.split(line, 4);

            var x1 = Integer.parseInt(nums[0]);
            var y1 = Integer.parseInt(nums[1]);
            var x2 = Integer.parseInt(nums[2]);
            var y2 = Integer.parseInt(nums[3]);

            var deltaX = x2 - x1;
            var deltaY = y2 - y1;

            if (!includeDiagonals && deltaX != 0 && deltaY != 0) {
                continue;
            }

            var magnitude = deltaX == 0 ? Math.abs(deltaY) : Math.abs(deltaX);
            deltaX = Integer.signum(deltaX);
            deltaY = Integer.signum(deltaY);

            for (int i = 0; i <= magnitude; i++) {
                gridCount += grid[(y1 + deltaY * i) * GRID_WIDTH + (x1 + deltaX * i)]++ == 1 ? 1 : 0;
            }
        }

        return gridCount;
    }


    private static int findDangerousAreas(List<String> input) {
        int[][] board = new int[findTopX(input) + 1][findTopY(input) + 1];
        for (String in : input) {
            int x1 = Integer.parseInt(in.split(" -> ")[0].split(",")[0].trim());
            int y1 = Integer.parseInt(in.split(" -> ")[0].split(",")[1].trim());

            int x2 = Integer.parseInt(in.split(" -> ")[1].split(",")[0].trim());
            int y2 = Integer.parseInt(in.split(" -> ")[1].split(",")[1].trim());

            if (x1 > x2) {
                int temp = x2;
                x2 = x1;
                x1 = temp;

                int tempY = y2;
                y2 = y1;
                y1 = tempY;
            }

            if (!isHorizontalOrVertical(x1, y1, x2, y2)) {
                continue;
            }
            for (int i = x1; i <= x2; i++) {
                if (y1 <= y2) {
                    for (int j = y1; j <= y2; j++) {
                        board[j][i]++;
                    }
                } else {
                    for (int j = y2; j <= y1; j++) {
                        board[j][i]++;
                    }
                }
            }
        }

        int result = 0;
        for (int[] row : board) {
            for (int cell : row) {
                if (cell > 1) {
                    result++;
                }
            }
        }


        return result;
    }

    private static int findTopX(List<String> input) {
        int max = 0;
        for (String in : input) {
            int y1 = Integer.parseInt(in.split(" -> ")[0].split(",")[1].trim());
            if (y1 > max) {
                max = y1;
            }
            int y2 = Integer.parseInt(in.split(" -> ")[1].split(",")[1].trim());
            if (y2 > max) {
                max = y2;
            }
        }
        return max;
    }

    private static int findTopY(List<String> input) {
        int max = 0;
        for (String in : input) {
            int x1 = Integer.parseInt(in.split(" -> ")[0].split(",")[0].trim());
            if (x1 > max) {
                max = x1;
            }
            int x2 = Integer.parseInt(in.split(" -> ")[1].split(",")[0].trim());
            if (x2 > max) {
                max = x2;
            }
        }
        return max;
    }

    private static boolean isHorizontalOrVertical(int x1, int y1, int x2, int y2) {
        return x1 == x2 || y1 == y2;
    }
}

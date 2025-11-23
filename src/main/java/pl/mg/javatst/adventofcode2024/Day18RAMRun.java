package pl.mg.javatst.adventofcode2024;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * <a href="https://adventofcode.com/2024/day/18">Advent of code 2024 day 18</a>
 *
 * Utility runner that simulates bytes dropping onto a memory grid and finds a shortest path
 * from a fixed START point to an END point using Breadth-First Search (BFS).
 */
@Slf4j
public class Day18RAMRun {

    private static final int GRID_SIZE = 71;

    private static final Point START = new Point(0, 0, 0);
    private static final Point END = new Point(70, 70, 0);

    private static final int[] DX = {0, 0, 1, -1};
    private static final int[] DY = {1, -1, 0, 0};


    /**
     * Entry point for this runner (non-standard signature used for internal invocation).
     *
     * This method performs the following steps:
     * - Reads input lines from resources (each line expected in the format "X,Y").
     * - Converts the first set of coordinates to integer pairs.
     * - Simulates dropping a configured number of bytes onto the memory grid.
     * - Prints the memory grid to stdout marking start (S), exit (E) and corrupted cells (#).
     * - Runs a BFS to determine the minimum number of steps from START to END and prints the result.
     *
     * Note: This method mutates local state and prints status to System.out/System.err; it does not
     * return a value so it is primarily intended for interactive or demo runs.
     */
    static void main() {
        System.out.println("Day 18 RAM Run");
        List<String> lines = Utils.readLines("src/main/resources/adventofcode/day18_input.txt");
        lines.forEach(log::debug);

        int[][] FIRST_12_BYTES = convertStringListToByteArray(lines);

        int bytesToSimulate = 1024; // As in the example

        // true = # (corrupted), false = . (safe)
        boolean[][] grid = new boolean[GRID_SIZE][GRID_SIZE];

        // Step 1: Simulate byte drops
        simulateByteDrops(grid, FIRST_12_BYTES, bytesToSimulate);

        System.out.println("--- Memory state after dropping " + bytesToSimulate + " bytes ---");
        for (int y = 0; y < GRID_SIZE; y++) {
            for (int x = 0; x < GRID_SIZE; x++) {
                if (x == START.x && y == START.y) {
                    System.out.print("S"); // Start
                } else if (x == END.x && y == END.y) {
                    System.out.print("E"); // Exit
                } else {
                    System.out.print(grid[x][y] ? "#" : ".");
                }
            }
            System.out.println();
        }
        System.out.println("----------------------------------------");


        // Step 2: Run BFS algorithm
        int minSteps = findMinSteps(grid);

        if (minSteps != -1) {
            System.out.println("Minimum number of steps to exit (as in the example): " + minSteps);
        } else {
            System.out.println("Cannot reach the exit.");
        }
    }


    /**
     * Converts a list of coordinate strings into a 2D int array.
     *
     * Each string in {@code byteList} is expected to be in the format "X,Y" where X and Y are integers.
     * The returned array has shape [byteList.size()][2] where element [i][0] is X and [i][1] is Y.
     *
     * Lines that do not match the expected format or cannot be parsed as integers are ignored and
     * will leave the corresponding entry in the returned array set to the default value 0.
     *
     * @param byteList list of coordinate strings (each "X,Y")
     * @return int[][] array of parsed coordinates
     */
    public static int[][] convertStringListToByteArray(List<String> byteList) {
        // Initialize result array with appropriate size.
        int[][] result = new int[byteList.size()][2];

        for (int i = 0; i < byteList.size(); i++) {
            String byteStr = byteList.get(i);
            // Split the string into X and Y using comma as separator
            String[] parts = byteStr.split(",");

            if (parts.length == 2) {
                try {
                    // Convert strings to integers
                    int x = Integer.parseInt(parts[0].trim());
                    int y = Integer.parseInt(parts[1].trim());

                    // Store in result array
                    result[i][0] = x;
                    result[i][1] = y;
                } catch (NumberFormatException e) {
                    System.err.println("Number conversion error for: " + byteStr + ". Ignoring.");
                    // You may handle this error differently, e.g. throw an exception
                }
            } else {
                System.err.println("Invalid line format: " + byteStr + ". Expected 'X,Y'.");
            }
        }

        return result;
    }

    /**
     * Finds the minimum number of steps from START to END using Breadth-First Search (BFS).
     *
     * The method treats {@code grid[x][y] == true} as a blocked/corrupted cell and will not traverse
     * such cells. Movement is allowed in four cardinal directions (up/down/left/right). The returned
     * value is the number of steps in the shortest path, or {@code -1} if no path exists.
     *
     * @param grid boolean grid where true indicates a blocked/corrupted cell
     * @return minimum number of steps from START to END, or -1 when unreachable
     */
    public static int findMinSteps(boolean[][] grid) {
        // table to check visited fields
        boolean[][] visited = new boolean[GRID_SIZE][GRID_SIZE];
        Queue<Point> queue = new LinkedList<>();

        // Start
        queue.add(START);
        visited[START.x][START.y] = true;

        while (!queue.isEmpty()) {
            Point current = queue.poll();

            // check finish
            if (current.x == END.x && current.y == END.y) {
                return current.steps;
            }

            // Move to neighbors
            for (int i = 0; i < 4; i++) {
                int nextX = current.x + DX[i];
                int nextY = current.y + DY[i];

                // 1. check limits
                if (nextX >= 0 && nextX < GRID_SIZE && nextY >= 0 && nextY < GRID_SIZE) {
                    // 2. check if not visited
                    if (!visited[nextX][nextY]) {
                        // 3. check if cell not corrupted
                        if (!grid[nextX][nextY]) {
                            visited[nextX][nextY] = true;
                            // add to the queue
                            queue.add(new Point(nextX, nextY, current.steps + 1));
                        }
                    }
                }
            }
        }

        return -1; // path not found
    }

    /**
     * Simulates dropping up to {@code count} bytes onto the {@code grid}.
     *
     * The {@code incomingBytes} array is expected to be in the same format as returned by
     * {@link #convertStringListToByteArray(List)} (shape [N][2]). Cells corresponding to the
     * coordinates will be marked as corrupted (true). Coordinates outside the grid bounds are
     * ignored.
     *
     * @param grid the boolean grid to mutate (true = corrupted)
     * @param incomingBytes array of coordinate pairs [x,y]
     * @param count maximum number of bytes from {@code incomingBytes} to apply
     */
    private static void simulateByteDrops(boolean[][] grid, int[][] incomingBytes, int count) {
        int maxBytes = Math.min(count, incomingBytes.length);
        for (int i = 0; i < maxBytes; i++) {
            int x = incomingBytes[i][0];
            int y = incomingBytes[i][1];
            // Ensure coordinates are within bounds (they should be)
            if (x >= 0 && x < GRID_SIZE && y >= 0 && y < GRID_SIZE) {
                grid[x][y] = true; // Corrupted
            }
        }
    }


    /**
     * Simple helper class representing a point on the grid and the number of steps taken to reach it.
     */
    private static class Point {
        int x;
        int y;
        int steps;

        /**
         * Construct a new Point.
         *
         * @param x x-coordinate (0-based)
         * @param y y-coordinate (0-based)
         * @param steps number of steps taken to reach this point
         */
        public Point(int x, int y, int steps) {
            this.x = x;
            this.y = y;
            this.steps = steps;
        }
    }

}

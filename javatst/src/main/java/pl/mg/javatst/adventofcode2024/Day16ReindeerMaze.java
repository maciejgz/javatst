package pl.mg.javatst.adventofcode2024;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Day16ReindeerMaze {
    static class State {
        int x, y, direction, cost;

        State(int x, int y, int direction, int cost) {
            this.x = x;
            this.y = y;
            this.direction = direction;
            this.cost = cost;
        }
    }

    private static final int[] DX = {0, 1, 0, -1}; // Right, Down, Left, Up
    private static final int[] DY = {1, 0, -1, 0};
    private static final int ROTATION_COST = 1000;

    public static int findLowestScore(char[][] maze) {
        int rows = maze.length;
        int cols = maze[0].length;

        State start = null;
        State end = null;

        // Find start and end positions
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (maze[i][j] == 'S') {
                    start = new State(i, j, 0, 0);
                }
                if (maze[i][j] == 'E') {
                    end = new State(i, j, -1, 0);
                }
            }
        }

        if (start == null || end == null) throw new IllegalArgumentException("Maze must have both 'S' and 'E'");

        PriorityQueue<State> pq = new PriorityQueue<>(Comparator.comparingInt(s -> s.cost));
        boolean[][][] visited = new boolean[rows][cols][4]; // Visited[x][y][direction]

        pq.add(start);

        while (!pq.isEmpty()) {
            State current = pq.poll();

            if (current.x == end.x && current.y == end.y) {
                return current.cost;
            }

            if (visited[current.x][current.y][current.direction]) continue;
            visited[current.x][current.y][current.direction] = true;

            // Try moving forward
            int nx = current.x + DX[current.direction];
            int ny = current.y + DY[current.direction];
            if (isInBounds(nx, ny, rows, cols) && maze[nx][ny] != '#' && !visited[nx][ny][current.direction]) {
                pq.add(new State(nx, ny, current.direction, current.cost + 1));
            }

            // Try rotating left and right
            int leftDirection = (current.direction + 3) % 4;
            int rightDirection = (current.direction + 1) % 4;

            if (!visited[current.x][current.y][leftDirection]) {
                pq.add(new State(current.x, current.y, leftDirection, current.cost + ROTATION_COST));
            }

            if (!visited[current.x][current.y][rightDirection]) {
                pq.add(new State(current.x, current.y, rightDirection, current.cost + ROTATION_COST));
            }
        }

        return -1; // If no path found
    }

    private static boolean isInBounds(int x, int y, int rows, int cols) {
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }

    public static void main(String[] args) {
        List<String> lines = Utils.readLines("src/main/resources/adventofcode/day16_input.txt");
        String[] input = lines.toArray(new String[0]);

        char[][] maze = new char[input.length][input[0].length()];
        for (int i = 0; i < input.length; i++) {
            maze[i] = input[i].toCharArray();
        }

        System.out.println("Lowest score: " + findLowestScore(maze));
    }
}

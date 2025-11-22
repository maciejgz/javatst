package pl.mg.javatst.adventofcode2024;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Slf4j
public class Day12GardenGroups {

    static class Region {
        char type;
        int area;
        int perimeter;

        Region(char type) {
            this.type = type;
            this.area = 0;
            this.perimeter = 0;
        }
    }


    public static void main(String[] args) {
        List<String> lines = Utils.readLines("src/main/resources/adventofcode/day12_input.txt");
        char[][] input = lines.stream().map(String::toCharArray).toArray(char[][]::new);
        log.debug("Total price: {}", calculateTotalPrice(input));
    }


    public static int calculateTotalPrice(char[][] map) {
        int rows = map.length;
        int cols = map[0].length;
        boolean[][] visited = new boolean[rows][cols];
        int totalPrice = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (!visited[i][j]) {
                    Region region = new Region(map[i][j]);
                    floodFill(map, visited, i, j, region);
                    int price = region.area * region.perimeter;
                    totalPrice += price;
                }
            }
        }

        return totalPrice;
    }

    private static void floodFill(char[][] map, boolean[][] visited, int x, int y, Region region) {
        int rows = map.length;
        int cols = map[0].length;
        char plantType = map[x][y];

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});
        visited[x][y] = true;

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int cx = current[0];
            int cy = current[1];

            region.area++;
            int localPerimeter = 4;

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if (nx >= 0 && nx < rows && ny >= 0 && ny < cols) {
                    if (map[nx][ny] == plantType) {
                        localPerimeter--;
                        if (!visited[nx][ny]) {
                            visited[nx][ny] = true;
                            queue.add(new int[]{nx, ny});
                        }
                    }
                }
            }

            region.perimeter += localPerimeter;
        }
    }

}

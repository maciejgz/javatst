package pl.mg.javatst.adventofcode2025;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import pl.mg.javatst.adventofcode2024.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Advent of code 2025 day 8
 *
 * @see <a href="https://adventofcode.com/2025/day/8">Advent of code 2025 day 8</a>
 */
@Slf4j
public class Day08Playground {


    static void main() {
        List<String> lines = Utils.readLines("src/main/resources/adventofcode2025/day08_input.txt");

        List<Point3D> points = new ArrayList<>();
        for (String line : lines) {
            String[] split = line.split(",");
            points.add(new Point3D(Double.parseDouble(split[0]), Double.parseDouble(split[1]), Double.parseDouble(split[2])));
        }

        // 2. Generowanie i sortowanie krawędzi
        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < points.size(); i++) {
            for (int j = i + 1; j < points.size(); j++) {
                edges.add(new Edge(i, j, points.get(i).distance(points.get(j))));
            }
        }
        edges.sort(Comparator.comparingDouble(e -> e.distance));

        // 3. DSU - Inicjalizacja
        int n = points.size();
        int[] parent = new int[n];
        int[] size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }

        // 4. Przetworzenie dokładnie 1000 najkrótszych krawędzi zgodnie z zadaniem
        int limit = Math.min(edges.size(), 1000);

        for (int i = 0; i < limit; i++) {
            Edge e = edges.get(i);
            int root1 = find(parent, e.idx1);
            int root2 = find(parent, e.idx2);

            if (root1 != root2) {
                // Union by size
                if (size[root1] < size[root2]) {
                    parent[root1] = root2;
                    size[root2] += size[root1];
                } else {
                    parent[root2] = root1;
                    size[root1] += size[root2];
                }
            }
            // Jeśli root1 == root2, krawędź jest "zużyta", ale nic się nie zmienia
        }

        // 5. Obliczenie wyniku
        List<Integer> finalSizes = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (parent[i] == i) finalSizes.add(size[i]);
        }
        finalSizes.sort(Collections.reverseOrder());

        long result = 1;
        for (int i = 0; i < Math.min(3, finalSizes.size()); i++) {
            result *= finalSizes.get(i);
        }

        System.out.println("Wynik: " + result);
    }

    private static int find(int[] parent, int i) {
        if (parent[i] == i) return i;
        return parent[i] = find(parent, parent[i]);
    }


    @Data
    @AllArgsConstructor
    private static class Point3D {
        double x;
        double y;
        double z;

        public double distance(Point3D other) {
            double dx = this.x - other.x;
            double dy = this.y - other.y;
            double dz = this.z - other.z;

            return Math.sqrt((dx * dx) + (dy * dy) + (dz * dz));
        }
    }

    @Data
    @AllArgsConstructor
    private static class Edge {
        int idx1;
        int idx2;
        double distance;
    }
}

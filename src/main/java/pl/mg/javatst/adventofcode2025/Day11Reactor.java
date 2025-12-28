package pl.mg.javatst.adventofcode2025;

import pl.mg.javatst.adventofcode2024.Utils;

import java.util.*;

public class Day11Reactor {

    public static void main(String[] args) {
        List<String> lines = Utils.readLines("src/main/resources/adventofcode2025/day11_input.txt");

        // Parse the graph
        Map<String, List<String>> graph = parseGraph(lines);

        // Count all paths from "you" to "out"
        int pathCount = countPaths(graph, "you", "out", new HashSet<>());

        System.out.println("Number of paths from 'you' to 'out': " + pathCount);
    }

    /**
     * Parses the input lines into a graph representation.
     * Each line format: "device: output1 output2 output3"
     */
    private static Map<String, List<String>> parseGraph(List<String> lines) {
        Map<String, List<String>> graph = new HashMap<>();

        for (String line : lines) {
            if (line.isBlank()) continue;

            String[] parts = line.split(":\\s*");
            String device = parts[0].trim();

            List<String> outputs = new ArrayList<>();
            if (parts.length > 1 && !parts[1].isBlank()) {
                String[] outputDevices = parts[1].trim().split("\\s+");
                outputs.addAll(Arrays.asList(outputDevices));
            }

            graph.put(device, outputs);
        }

        return graph;
    }

    /**
     * Counts all paths from start to end using DFS.
     * Uses visited set to avoid cycles.
     */
    private static int countPaths(Map<String, List<String>> graph, String current, String target, Set<String> visited) {
        // If we reached the target, we found one path
        if (current.equals(target)) {
            return 1;
        }

        // If this device has no outputs or doesn't exist in graph, no path from here
        if (!graph.containsKey(current)) {
            return 0;
        }

        // Mark current as visited to avoid cycles
        visited.add(current);

        int totalPaths = 0;
        List<String> outputs = graph.get(current);

        for (String output : outputs) {
            // Only visit if not already in current path (avoid cycles)
            if (!visited.contains(output)) {
                totalPaths += countPaths(graph, output, target, visited);
            }
        }

        // Backtrack - remove from visited to allow other paths
        visited.remove(current);

        return totalPaths;
    }
}

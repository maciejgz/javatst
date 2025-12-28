package pl.mg.javatst.adventofcode2025;

import lombok.extern.slf4j.Slf4j;
import pl.mg.javatst.adventofcode2024.Utils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class Day10Factory {

    static void main() {
        List<String> lines = Utils.readLines("src/main/resources/adventofcode2025/day10_input.txt");

        long totalPresses = 0;
        for (String line : lines) {
            int presses = solveMachine(line);
            log.info("Machine: {} -> {} presses", line, presses);
            totalPresses += presses;
        }

        log.info("Total minimum button presses: {}", totalPresses);
    }

    static int solveMachine(String line) {
        // Parse the line
        // Format: [.##.] (3) (1,3) (2) (2,3) (0,2) (0,1) {3,5,4,7}

        // Extract indicator diagram [...]
        Pattern diagramPattern = Pattern.compile("\\[([.#]+)]");
        Matcher diagramMatcher = diagramPattern.matcher(line);
        if (!diagramMatcher.find()) {
            throw new IllegalArgumentException("No diagram found in line: " + line);
        }
        String diagram = diagramMatcher.group(1);
        int numLights = diagram.length();

        // Target state (true = on = #)
        boolean[] target = new boolean[numLights];
        for (int i = 0; i < numLights; i++) {
            target[i] = diagram.charAt(i) == '#';
        }

        // Extract button schematics (...)
        // We need to find all (...) but not {...}
        Pattern buttonPattern = Pattern.compile("\\(([0-9,]*)\\)");
        Matcher buttonMatcher = buttonPattern.matcher(line);
        List<Set<Integer>> buttons = new ArrayList<>();

        while (buttonMatcher.find()) {
            String content = buttonMatcher.group(1);
            Set<Integer> toggles = new HashSet<>();
            if (!content.isEmpty()) {
                for (String num : content.split(",")) {
                    toggles.add(Integer.parseInt(num.trim()));
                }
            }
            buttons.add(toggles);
        }

        // Now we need to find the minimum number of buttons to press
        // This is essentially solving a system of linear equations over GF(2)
        // We can use BFS or try all combinations with pruning

        // Since each button press toggles, pressing twice is same as not pressing
        // So we only need to consider 0 or 1 press per button
        // Total combinations = 2^numButtons

        int numButtons = buttons.size();

        // For small number of buttons, brute force is fine
        // For larger, we'd need Gaussian elimination over GF(2)

        if (numButtons <= 20) {
            return bruteForce(target, buttons, numLights);
        } else {
            return gaussianElimination(target, buttons, numLights);
        }
    }

    static int bruteForce(boolean[] target, List<Set<Integer>> buttons, int numLights) {
        int numButtons = buttons.size();
        int minPresses = Integer.MAX_VALUE;

        // Try all 2^numButtons combinations
        for (int mask = 0; mask < (1 << numButtons); mask++) {
            boolean[] state = new boolean[numLights]; // all initially off
            int presses = 0;

            for (int b = 0; b < numButtons; b++) {
                if ((mask & (1 << b)) != 0) {
                    presses++;
                    // Toggle all lights controlled by this button
                    for (int lightIndex : buttons.get(b)) {
                        if (lightIndex < numLights) {
                            state[lightIndex] = !state[lightIndex];
                        }
                    }
                }
            }

            // Check if state matches target
            if (Arrays.equals(state, target)) {
                minPresses = Math.min(minPresses, presses);
            }
        }

        return minPresses == Integer.MAX_VALUE ? -1 : minPresses;
    }

    static int gaussianElimination(boolean[] target, List<Set<Integer>> buttons, int numLights) {
        // For larger inputs, use Gaussian elimination over GF(2)
        // Build augmented matrix [A | b] where A is button effects, b is target

        int numButtons = buttons.size();

        // Create matrix: rows = lights, cols = buttons + 1 (for target)
        int[][] matrix = new int[numLights][numButtons + 1];

        for (int b = 0; b < numButtons; b++) {
            for (int light : buttons.get(b)) {
                if (light < numLights) {
                    matrix[light][b] = 1;
                }
            }
        }

        for (int i = 0; i < numLights; i++) {
            matrix[i][numButtons] = target[i] ? 1 : 0;
        }

        // Gaussian elimination over GF(2)
        int[] pivotCol = new int[numLights];
        Arrays.fill(pivotCol, -1);

        int col = 0;
        for (int row = 0; row < numLights && col < numButtons; ) {
            // Find pivot
            int pivotRow = -1;
            for (int r = row; r < numLights; r++) {
                if (matrix[r][col] == 1) {
                    pivotRow = r;
                    break;
                }
            }

            if (pivotRow == -1) {
                col++;
                continue;
            }

            // Swap rows
            int[] temp = matrix[row];
            matrix[row] = matrix[pivotRow];
            matrix[pivotRow] = temp;

            pivotCol[row] = col;

            // Eliminate
            for (int r = 0; r < numLights; r++) {
                if (r != row && matrix[r][col] == 1) {
                    for (int c = 0; c <= numButtons; c++) {
                        matrix[r][c] ^= matrix[row][c];
                    }
                }
            }

            row++;
            col++;
        }

        // Check for inconsistency
        for (int r = 0; r < numLights; r++) {
            boolean allZero = true;
            for (int c = 0; c < numButtons; c++) {
                if (matrix[r][c] == 1) {
                    allZero = false;
                    break;
                }
            }
            if (allZero && matrix[r][numButtons] == 1) {
                return -1; // No solution
            }
        }

        // Find free variables and try to minimize number of 1s in solution
        List<Integer> freeVars = new ArrayList<>();
        Set<Integer> pivotVars = new HashSet<>();
        for (int r = 0; r < numLights; r++) {
            if (pivotCol[r] != -1) {
                pivotVars.add(pivotCol[r]);
            }
        }

        for (int c = 0; c < numButtons; c++) {
            if (!pivotVars.contains(c)) {
                freeVars.add(c);
            }
        }

        // Try all combinations of free variables
        int minPresses = Integer.MAX_VALUE;
        int numFree = freeVars.size();

        for (int freeMask = 0; freeMask < (1 << numFree); freeMask++) {
            int[] solution = new int[numButtons];

            // Set free variables
            for (int f = 0; f < numFree; f++) {
                solution[freeVars.get(f)] = (freeMask >> f) & 1;
            }

            // Back substitute to find pivot variables
            for (int r = numLights - 1; r >= 0; r--) {
                if (pivotCol[r] != -1) {
                    int pc = pivotCol[r];
                    int val = matrix[r][numButtons];
                    for (int c = pc + 1; c < numButtons; c++) {
                        val ^= (matrix[r][c] * solution[c]);
                    }
                    solution[pc] = val;
                }
            }

            int presses = 0;
            for (int s : solution) {
                presses += s;
            }
            minPresses = Math.min(minPresses, presses);
        }

        return minPresses;
    }
}

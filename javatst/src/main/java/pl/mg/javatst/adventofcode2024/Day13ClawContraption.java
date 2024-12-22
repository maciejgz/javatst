package pl.mg.javatst.adventofcode2024;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class Day13ClawContraption {


    static class Machine {
        int ax, ay, bx, by; // Button A and B movements
        int px, py;         // Prize location

        Machine(int ax, int ay, int bx, int by, int px, int py) {
            this.ax = ax;
            this.ay = ay;
            this.bx = bx;
            this.by = by;
            this.px = px;
            this.py = py;
        }
    }

    public static void main(String[] args) {
        List<Machine> machines = new ArrayList<>();

        List<String> lines = Utils.readLines("src/main/resources/adventofcode/day13_input.txt");

        for (int i = 0; i < lines.size(); i += 4) {
            Pattern patternA = Pattern.compile("X\\+(\\d+), Y\\+(\\d+)");
            Matcher matcherA = patternA.matcher(lines.get(i));

            Pattern patternB = Pattern.compile("X\\+(\\d+), Y\\+(\\d+)");
            Matcher matcherB = patternB.matcher(lines.get(i + 1));

            Pattern patternC = Pattern.compile("X=(\\d+), Y=(\\d+)");
            Matcher matcherC = patternC.matcher(lines.get(i + 2));

            int ax = 0;
            int ay = 0;
            if (matcherA.find()) {
                ax = Integer.parseInt(matcherA.group(1));
                ay = Integer.parseInt(matcherA.group(2));
            }

            int bx = 0;
            int by = 0;
            if (matcherB.find()) {
                bx = Integer.parseInt(matcherB.group(1));
                by = Integer.parseInt(matcherB.group(2));
            }
            int px = 0;
            int py = 0;
            if (matcherC.find()) {
                px = Integer.parseInt(matcherC.group(1));
                py = Integer.parseInt(matcherC.group(2));
            }

            machines.add(new Machine(ax, ay, bx, by, px, py));
            log.debug("Machine: A({}, {}) B({}, {}) Prize({}, {})", ax, ay, bx, by, px, py);
            log.debug("line {}", i);
        }

        int maxPrizes = 0;
        int minTokens = 0;

        for (Machine machine : machines) {
            int[] result = solveMachine(machine, 100);
            if (result != null) {
                maxPrizes++;
                minTokens += result[0];
            }
        }

        System.out.println("Maximum Prizes: " + maxPrizes);
        System.out.println("Minimum Tokens: " + minTokens);
    }

    // Solve for a single machine within the given limit of button presses
    static int[] solveMachine(Machine machine, int maxPresses) {
        int ax = machine.ax, ay = machine.ay, bx = machine.bx, by = machine.by;
        int px = machine.px, py = machine.py;

        int minTokens = Integer.MAX_VALUE;
        int bestA = 0, bestB = 0;

        // Brute force approach: try all combinations of A and B presses
        for (int a = 0; a <= maxPresses; a++) {
            for (int b = 0; b <= maxPresses; b++) {
                int x = a * ax + b * bx;
                int y = a * ay + b * by;
                if (x == px && y == py) {
                    int cost = a * 3 + b * 1; // Cost of A and B button presses
                    if (cost < minTokens) {
                        minTokens = cost;
                        bestA = a;
                        bestB = b;
                    }
                }
            }
        }

        if (minTokens == Integer.MAX_VALUE) {
            return null; // No solution found
        }

        System.out.println("Solved Machine: A=" + bestA + ", B=" + bestB + ", Tokens=" + minTokens);
        return new int[]{minTokens, bestA, bestB};
    }
}

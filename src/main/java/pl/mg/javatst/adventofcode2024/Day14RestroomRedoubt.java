package pl.mg.javatst.adventofcode2024;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day14RestroomRedoubt {

    public static void main(String[] args) {
        // Define the simulation parameters
        int width = 101;
        int height = 103;
        int timeSteps = 100;


        List<String> lines = Utils.readLines("src/main/resources/adventofcode/day14_input.txt");

        // Input list of robots
        List<Robot> robots = new ArrayList<>();
        Pattern pattern = Pattern.compile("p=(\\d+),(\\d+) v=(-?\\d+),(-?\\d+)");

        for (String line : lines) {
            Matcher matcher = pattern.matcher(line);
            if (matcher.find()) {
                int x = Integer.parseInt(matcher.group(1));
                int y = Integer.parseInt(matcher.group(2));
                int xSpeed = Integer.parseInt(matcher.group(3));
                int ySpeed = Integer.parseInt(matcher.group(4));
                robots.add(new Robot(x, y, xSpeed, ySpeed));
            }
        }

        // Simulate the movement for the given number of seconds
        for (int t = 0; t < timeSteps; t++) {
            for (Robot robot : robots) {
                robot.move(width, height);
            }
        }

        // Count robots in each quadrant
        int halfWidth = width / 2;
        int halfHeight = height / 2;
        int q1 = 0, q2 = 0, q3 = 0, q4 = 0;

        for (Robot robot : robots) {
            if (robot.px == halfWidth || robot.py == halfHeight) {
                // Robots on middle lines are ignored
                continue;
            } else if (robot.px > halfWidth && robot.py < halfHeight) {
                q1++;
            } else if (robot.px < halfWidth && robot.py < halfHeight) {
                q2++;
            } else if (robot.px < halfWidth && robot.py > halfHeight) {
                q3++;
            } else if (robot.px > halfWidth && robot.py > halfHeight) {
                q4++;
            }
        }

        // Calculate the safety factor
        int safetyFactor = q1 * q2 * q3 * q4;

        // Output the result
        System.out.println("Quadrant counts: Q1=" + q1 + ", Q2=" + q2 + ", Q3=" + q3 + ", Q4=" + q4);
        System.out.println("Safety Factor: " + safetyFactor);
    }

    static class Robot {
        int px, py, vx, vy;

        public Robot(int px, int py, int vx, int vy) {
            this.px = px;
            this.py = py;
            this.vx = vx;
            this.vy = vy;
        }

        // Update position with teleporting at edges
        public void move(int width, int height) {
            px = (px + vx + width) % width;
            py = (py + vy + height) % height;
        }
    }
}

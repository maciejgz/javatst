package pl.mg.javatst.adventofcode;

import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Day 2 - Dive!
 */
public class Dive2 {

    public static void main(String[] args) {
        List<String> input = null;
        try {
            input = Files.readLines(new File("C:\\workspace\\dive.txt"), StandardCharsets.UTF_8);
            long result = dive(input);
            System.out.println("dive: " + result);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static long dive(List<String> input) {
        long horizontalPosition = 0;
        long depth = 0;
        long aim = 0;
        for (String command : input) {
            long value = Long.parseLong(command.split(" ")[1]);
            if (command.startsWith("forward")) {
                horizontalPosition += value;
                depth += (aim * value);
            } else if (command.startsWith("down")) {
                aim += value;
            } else {
                aim -= value;
            }
        }
        return horizontalPosition * depth;
    }
}

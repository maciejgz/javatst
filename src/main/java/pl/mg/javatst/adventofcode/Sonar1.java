package pl.mg.javatst.adventofcode;

import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Day one
 */
public class Sonar1 {

    public static void main(String[] args) {
        try {
            List<String> levels = Files.readLines(new File("C:\\workspace\\input.txt"), StandardCharsets.UTF_8);
            int level = 0;
            int result = 0;

            for (String currentLevel : levels) {
                if (level != 0 && Integer.parseInt(currentLevel) > level) {
                    result++;
                }
                level = Integer.parseInt(currentLevel);
            }
            System.out.println(result);
            System.out.println("Sliding windows levels: " + slidingWindow(levels));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static int slidingWindow(List<String> levels) {
        int result = 0;
        int[] window = new int[3];

        for (String level : levels) {
            if (window[0] == 0 && window[1] == 0 && window[2] == 0) {
                window[2] = Integer.parseInt(level);
                continue;
            } else if (window[0] == 0 || window[1] == 0 || window[2] == 0) {
                window[0] = window[1];
                window[1] = window[2];
                window[2] = Integer.parseInt(level);
                continue;
            }

            if (window[0] + window[1] + window[2] < window[1] + window[2] + Integer.parseInt(level)) {
                result++;
            }

            window[0] = window[1];
            window[1] = window[2];
            window[2] = Integer.parseInt(level);
        }
        return result;
    }

}

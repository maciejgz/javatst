package pl.mg.javatst.adventofcode2024;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Utils {

    public static List<String> readLines(String filePath) {
        Path path = Paths.get(filePath);
        try {
            return Files.readAllLines(path);
        } catch (IOException e) {
            log.debug("Error reading input", e);
            return new ArrayList<>();
        }
    }

}

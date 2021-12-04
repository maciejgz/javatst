package pl.mg.javatst.adventofcode;

import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class FileUtil {

    public static List<String> readLines(String pathToResources) throws IOException {
        ClassLoader classLoader = BinaryDiagnostic3.class.getClassLoader();
        File file = new File(classLoader.getResource(pathToResources).getFile());
        return Files.readLines(file, StandardCharsets.UTF_8);
    }
}

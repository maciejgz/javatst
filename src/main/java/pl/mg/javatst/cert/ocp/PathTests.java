package pl.mg.javatst.cert.ocp;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class PathTests {

    public static void main(String[] args) throws IOException {
        Path path1 = Paths.get("resources/sample_file.txt");
        System.out.println(path1.getParent().toString());
        System.out.println(path1.normalize().toFile().exists());
        System.out.println(Files.exists(path1));

        Stream<Path> strea = Files.walk(Paths.get("C:\\workspace\\git\\javatst"));
        strea
                .filter(w ->
                {
                    System.out.println("sa: " + w.getFileName());
                    return Files.isRegularFile(w) && w.getFileName().endsWith(".java");
                })
                .forEach(p -> System.out.println(p.getFileName()));
    }
}

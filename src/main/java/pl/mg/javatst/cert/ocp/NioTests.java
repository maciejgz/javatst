package pl.mg.javatst.cert.ocp;

import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class NioTests {

    public static void main(String[] args) {
        FileSystem fs = FileSystems.getDefault();
        System.out.println(fs.getSeparator());
        Path sample = Path.of("sample.txt");

        Path back = Path.of("C:\\sample\\sample");
    }
}

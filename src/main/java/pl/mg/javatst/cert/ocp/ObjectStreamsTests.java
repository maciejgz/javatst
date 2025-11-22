package pl.mg.javatst.cert.ocp;

import java.io.*;

public class ObjectStreamsTests {

    public static void main(String[] args) {
    }

    public void copy(File input, File output) {
        try (InputStream inputStream = new FileInputStream(input);
             OutputStream outputStream = new FileOutputStream(output);
        ) {
            int b = 0;
            while ((b = inputStream.read()) != -1) {
                outputStream.write(b);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

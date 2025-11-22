package pl.mg.javatst.cert.ocp;

import java.io.Closeable;
import java.io.IOException;

public class AutoCloseablePlays implements Closeable {
    public static void main(String[] args) {
        try (AutoCloseablePlays plays = new AutoCloseablePlays()) {

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws IOException {
        System.out.println("closed");
    }
}

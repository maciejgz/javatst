package pl.mg.javatst.certificate.oca;

import java.util.ArrayList;
import java.util.Collections;

public class FunctionalInterfaceSample {

    public static void main(String[] args) {
        Collections.sort(new ArrayList<String>(), (o1, o2) -> {
            return -1;
        });
    }
}

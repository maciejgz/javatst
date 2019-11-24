package pl.mg.javatst.cert.ocp;

import java.util.Comparator;

public class DuckMethodReferenceTest {

    public static void main(String[] args) {
        Duck d1 = Duck.builder().name("duck1").weight(1).build();
        Duck d2 = Duck.builder().name("duck2").weight(2).build();

        Comparator<Duck> comp1 = DuckHelper::compareByName;

    }

}

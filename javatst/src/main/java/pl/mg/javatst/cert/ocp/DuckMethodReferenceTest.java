package pl.mg.javatst.cert.ocp;

import java.util.Comparator;

public class DuckMethodReferenceTest {

    public static void main(String[] args) {
        Duck d1 = new Duck("duck1", 1);
        Duck d2 = new Duck("duck2", 2);

        Comparator<Duck> comp1 = DuckHelper::compareByName;

    }

}

package pl.mg.javatst.cert.ocp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ComparatorTesters {

    public static void main(String[] args) {
        stringSort();
    }

    private static void stringSort() {
        List<String> strs = new ArrayList<>();
        strs.add("AbA");
        strs.add("aba");
        strs.add("123");

        Collections.sort(strs);
        System.out.println(strs);
    }
}

package pl.mg.javatst.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class NewTestClass {
    public static void main(String args[]) {
        B c = new C();
        System.out.println(c.max(10, 20));
        LocalDate d1 = LocalDate.parse("2015-02-05", DateTimeFormatter.ISO_DATE);
        LocalDate d2 = LocalDate.of(2015, 2, 5);
        LocalDate d3 = LocalDate.now();
        System.out.println(d1);
        System.out.println(d2);
        System.out.println(d3);

        int i = 4;
        int ia[][][] = new int[i][i = 3][i];
        System.out.println(ia.length + ", " + ia[0].length + ", " + ia[0][0].length);

        List s1 = new ArrayList();
        try {
            while (true) {
                s1.add("sdfa");
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        System.out.println(s1.size());
    }


}

class A {
    int max(int x, int y) {
        if (x > y) return x;
        else return y;
    }
}

class B extends A {
    int max(int x, int y) {
        return 2 * super.max(x, y);
    }
}

class C extends B {
    int max(int x, int y) {
        return super.max(2 * x, 2 * y);
    }
}

package pl.mg.javatst.clone;

import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Clone tests
 */
public class CloneTests {


    public static void main(String[] args) {
        CloneTests.cloneStringArray();
        try {
            CloneTests.deepClone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }


    private static void cloneStringArray() {
        String[] original = new String[2];
        original[0] = "zero";
        original[1] = "one";

        String[] copy = ArrayUtils.clone(original);

        copy[1] = "two";

        System.out.println(Arrays.toString(original));
        System.out.println(Arrays.toString(copy));
        System.out.println("----");
    }

    private static void deepClone() throws CloneNotSupportedException {
        List<VapSource> vapSources1 = new ArrayList<>();
        vapSources1.add(new VapSource("source1", 1));
        Vap vap1 = new Vap("1", 1, vapSources1);

        List<Vap> vaps = new ArrayList<>();
        vaps.add(vap1);

        for (Vap vap : vaps) {
            System.out.println(vap);
        }

        List<Vap> clonedVaps = new ArrayList<>();
        for (Vap vap : vaps) {
            clonedVaps.add((Vap) vap.clone());
        }

        clonedVaps.get(0).setName("cloned");
        clonedVaps.get(0).getVapSources().get(0).setSourceName("clonedName");


        System.out.println("original:");
        for (Vap vap : vaps) {
            System.out.println(vap);
        }

        System.out.println("cloned:");
        for (Vap vap : clonedVaps) {
            System.out.println(vap);
        }

        System.out.println("----");
    }
}

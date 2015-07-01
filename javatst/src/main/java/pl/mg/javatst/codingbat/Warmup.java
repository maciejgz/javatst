package pl.mg.javatst.codingbat;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by m on 2015-05-16.
 */
public class Warmup {


    @Test
    public void missingCharTest() {
        assertEquals(missingChar(), "eststring");
    }

    private static String missingChar() {
        String str = "teststring";
        int n = 0;
        if (n == 0) {
            return str.substring(1);
        } else if (n == str.length() - 1) {
            return str.substring(0, str.length() - 2);
        } else {
            return str.substring(0, n - 1) + str.substring(n + 1, str.length() - 1);
        }
    }


    private static boolean front3(String str) {
       return str.startsWith("hi");
    }
}

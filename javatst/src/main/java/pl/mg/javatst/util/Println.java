package pl.mg.javatst.util;

/**
 * Created by m on 2015-02-26.
 */
public class Println {

    public static void print(Object obj) {
        System.out.println(obj.toString());
    }

    public static void testInts(Integer obj, int var) {
        obj = var++;
        obj++;
    }

    public static void main(String[] args) {
        Integer val1 = new Integer(5);
        int val2 = 9;
        testInts(val1++, ++val2);
        System.out.println(val1 + " " + val2);
    }

}

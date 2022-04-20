package pl.mg.javatst.codility;

/**
 * https://www.codewars.com/kata/520b9d2ad5c005041100000f/train/java
 *
 * Solved 100%;
 */
public class SimplePigLatin {

    public static String pigIt(String str) {
        return str.replaceAll("([0-9a-zA-Z])([0-9a-zA-Z]+)?", "$2$1" + "ay");
    }

}

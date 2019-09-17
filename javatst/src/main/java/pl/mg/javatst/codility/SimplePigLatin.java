package pl.mg.javatst.codility;

/**
 * https://www.codewars.com/kata/520b9d2ad5c005041100000f/train/java
 *
 * Move the first letter of each word to the end of it, then add "ay" to the end of the word. Leave punctuation marks
 * untouched.
 *
 * Examples pigIt('Pig latin is cool'); // igPay atinlay siay oolcay pigIt('Hello world !');     // elloHay orldway !
 *
 * Solved 100%;
 */
public class SimplePigLatin {

    public static String pigIt(String str) {
        return str.replaceAll("([0-9a-zA-Z])([0-9a-zA-Z]+)?", "$2$1" + "ay");
    }

}

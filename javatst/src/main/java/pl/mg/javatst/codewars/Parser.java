package pl.mg.javatst.codewars;


import java.util.HashMap;
import java.util.Map;

/**
 * In this kata we want to convert a string into an integer. The strings simply represent the numbers in words.
 * <p>
 * Examples:
 * <p>
 * "one" => 1
 * "twenty" => 20
 * "two hundred forty-six" => 246
 * "seven hundred eighty-three thousand nine hundred and nineteen" => 783919
 * Additional Notes:
 * <p>
 * The minimum number is "zero" (inclusively)
 * The maximum number, which must be supported is 1 million (inclusively)
 * The "and" in e.g. "one hundred and twenty-four" is optional, in some cases it's present and in others it's not
 * All tested numbers are valid, you don't need to validate them
 */
public class Parser {

    public static void main(String[] args) {
        System.out.println(parseInt("one"));
        System.out.println(parseInt("twenty"));
        System.out.println(parseInt("two hundred forty-six"));
        System.out.println(parseInt("seven hundred eighty-three thousand nine hundred and nineteen"));
        System.out.println(parseInt("seven hundred eighty-three thousand nine hundred and forty-one"));
        System.out.println(parseInt("seven hundred seventy-one thousand nine hundred and thirty"));
        System.out.println(parseInt("seven hundred seventy-one thousand"));
        System.out.println(parseInt("one million"));
        System.out.println(parseInt("zero"));
    }

    public static int parseInt(String numStr) {
        int result = 0;
        if (numStr.equals("zero")) {
            return 0;
        }
        if (numStr.equals("one million")) {
            return 1000000;
        }
        numStr = numStr.replaceAll(" and", "");

        //thousand split
        String[] thousands = numStr.split(" thousand ");
        if (thousands.length > 1) {
            result += 1000 * parseToHundred(thousands[0].trim());
            result += parseToHundred(thousands[1].trim());
        } else {
            if (numStr.contains("thousand")) {
                result += 1000 * parseToHundred(numStr.replaceAll("thousand", "").trim());
            } else {
                result += parseToHundred(numStr.replaceAll("thousand", "").trim());
            }
        }
        return result;
    }

    public static int parseToHundred(String toHundred) {
        //check if hundred
        int result = 0;
        String[] hundreds = toHundred.split(" hundred ");
        if (hundreds.length > 1) {
            result += 100 * parseSingleNumber(hundreds[0].trim());
            result += parseEnd(hundreds[1].trim());
        } else {
            if (toHundred.contains("hundred")) {
                result += 100 * parseEnd(toHundred.replaceAll("hundred", "").trim());
            } else {
                result += parseEnd(toHundred.replaceAll("hundred", "").trim());
            }
        }
        return result;
    }

    private static int parseEnd(String number) {
        int result = 0;
        if (number.split("-").length > 1) {
            result += parseSingleNumber(number.trim().split("-")[0]);
            result += parseSingleNumber(number.trim().split("-")[1]);
        } else {
            result += parseSingleNumber(number);
        }
        return result;
    }

    public static final Map<String, Integer> SINGLE = new HashMap<>();

    static {
        SINGLE.put("one", 1);
        SINGLE.put("two", 2);
        SINGLE.put("three", 3);
        SINGLE.put("four", 4);
        SINGLE.put("five", 5);
        SINGLE.put("six", 6);
        SINGLE.put("seven", 7);
        SINGLE.put("eight", 8);
        SINGLE.put("nine", 9);
        SINGLE.put("ten", 10);
        SINGLE.put("eleven", 11);
        SINGLE.put("twelve", 12);
        SINGLE.put("thirteen", 13);
        SINGLE.put("fourteen", 14);
        SINGLE.put("fifteen", 15);
        SINGLE.put("sixteen", 16);
        SINGLE.put("seventeen", 17);
        SINGLE.put("eighteen", 18);
        SINGLE.put("nineteen", 19);
        SINGLE.put("twenty", 20);
        SINGLE.put("thirty", 30);
        SINGLE.put("forty", 40);
        SINGLE.put("fifty", 50);
        SINGLE.put("sixty", 60);
        SINGLE.put("seventy", 70);
        SINGLE.put("eighty", 80);
        SINGLE.put("ninety", 90);
        SINGLE.put("", 0);

    }

    private static int parseSingleNumber(String single) {
        return SINGLE.get(single.trim());
    }
}

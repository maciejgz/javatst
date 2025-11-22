package pl.mg.javatst.regextests;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by m on 2015-10-12.
 */
public class RegexpTests {

    public static void main(String[] args) {
        Pattern pattern =
                Pattern.compile("[t]");

        Matcher matcher =
                pattern.matcher("Enter input string to search: ");

        boolean found = false;
        while (matcher.find()) {
            System.out.printf("I found the text" +
                            " \"%s\" starting at " +
                            "index %d and ending at index %d.%n",
                    matcher.group(),
                    matcher.start(),
                    matcher.end());
            found = true;
        }
    }

}

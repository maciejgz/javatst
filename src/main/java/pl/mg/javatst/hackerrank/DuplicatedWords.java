package pl.mg.javatst.hackerrank;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DuplicatedWords {

    public static void main(String[] args) {
        String regex = "\\b(\\w+)(?:\\W+\\1\\b)+";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        String input = "text text aaa www www aaa aaa";
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            input = input.replaceAll(matcher.group(), matcher.group(1));
        }
        System.out.println(input);
    }

}

package pl.mg.javatst.hackerrank;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TagContentExtractor {

    public static void main(String[] args) {
        String line = "<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<";


        boolean matchFound = false;
        Pattern r = Pattern.compile("<(.+)>([^<]+)</\\1>");
        Matcher m = r.matcher(line);

        while (m.find()) {
            System.out.println(m.group(2));
            matchFound = true;
        }
        if (!matchFound) {
            System.out.println("None");
        }
    }
}

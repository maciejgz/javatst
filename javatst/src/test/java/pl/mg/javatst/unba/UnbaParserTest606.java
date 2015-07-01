package pl.mg.javatst.unba;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import pl.mg.javatst.util.Println;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by m on 2015-04-24.
 */
public class UnbaParserTest606 {

    XMLParser parser = null;
    String xmlToParse = null;
    String fileName = "606238233.txt";

    @Before
    public void before() {

        ClassLoader classLoader = getClass().getClassLoader();

        try {
            xmlToParse = IOUtils.toString(classLoader.getResourceAsStream(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        xmlToParse = xmlToParse.replaceAll("[^\\x20-\\x7e]", "");
        parser = new XMLParser(Logger.getLogger(UnbaParserTest606.class), xmlToParse, "606238233");
    }

    @Test
    public void test() {
        try {
            parser.parse();
            Println.print(parser.getRatingScenarios());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

package pl.mg.javatst.unba;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import pl.mg.javatst.util.Println;

import java.io.IOException;

/**
 * Created by m on 2015-04-24.
 */
public class UnbaParserMultipleCountersTest {

    XMLParser parser = null;
    String xmlToParse = null;
    String fileName = "664409160.txt";

    @Before
    public void before() {

        ClassLoader classLoader = getClass().getClassLoader();

        try {
            xmlToParse = IOUtils.toString(classLoader.getResourceAsStream(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        xmlToParse = xmlToParse.replaceAll("[^\\x20-\\x7e]", "");
        parser = new XMLParser(Logger.getLogger(UnbaParserMultipleCountersTest.class), xmlToParse, "664409160");
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

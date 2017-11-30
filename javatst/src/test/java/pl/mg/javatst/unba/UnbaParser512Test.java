package pl.mg.javatst.unba;

import org.apache.log4j.Logger;
import org.junit.*;
import org.junit.Test;
import pl.mg.javatst.util.Println;

import java.io.*;
import java.util.Scanner;

/**
 * Created by m on 2015-04-24.
 */
public class UnbaParser512Test {

    XMLParser parser = null;
    String xmlToParse = null;
    String fileName = "512127848.txt";

    @Before
    public void before() {

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());

        StringBuffer buffer = new StringBuffer();
        try {
            Scanner scanner = new Scanner(new InputStreamReader(new FileInputStream(file), "UTF-8"));

            while (scanner.hasNextLine()) {
                buffer.append(scanner.nextLine());
            }
            scanner.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        xmlToParse = buffer.toString();
        //remove invalid char
        xmlToParse = xmlToParse.replaceAll("[^\\x20-\\x7e]", "");
//        Println.print(xmlToParse);
        parser = new XMLParser(Logger.getLogger(UnbaParser606Test.class), xmlToParse, "512127848");

    }

    @Test
    public void test() {
        try {
            parser.parse();

            Println.print(parser.getRatingScenarios());


            GetUnbillDataBean bean = new GetUnbillDataBean();
            StringBuffer builder = new StringBuffer();
            bean.rewriteRatingScenarioToVoiceObjectsCollection(parser.getRatingScenarios(), builder);
            System.out.println(builder.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

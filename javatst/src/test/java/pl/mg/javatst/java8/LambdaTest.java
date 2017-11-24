package pl.mg.javatst.java8;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class LambdaTest {

    @Test
    public void emptyHiddenFileList7VersionTest() {
        FirstSample lambda = new FirstSample();
        File[] hiddenFiles7 = lambda.findHiddenFiles7(".");
        assertEquals(0, hiddenFiles7.length);
    }

    @Test
    public void emptyHiddenFileList8VersionTest() {
        FirstSample lambda = new FirstSample();
        // :: is a method reference
        File[] hiddenFiles7 = lambda.findHiddenFiles8(".");
        assertEquals(0, hiddenFiles7.length);
    }

}

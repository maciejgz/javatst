package pl.mg.javatst.codility;

import org.junit.Assert;
import org.junit.Test;

public class SimplePigLatinTest {

    @Test
    public void pigIt() {
        String sample = "This is my string";
        Assert.assertEquals("hisTay siay ymay tringsay", SimplePigLatin.pigIt(sample));


        String sample2 = "0 this sample";
        Assert.assertEquals("0ay histay amplesay", SimplePigLatin.pigIt(sample2));
    }
}
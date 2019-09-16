package pl.mg.javatst.hackerrank;

import org.junit.Assert;
import org.junit.Test;

public class StringsMakingAnagramsTest {

    @Test
    public void makeAnagram() {
        String a = "cde";
        String b = "abc";
        Assert.assertEquals(4, StringsMakingAnagrams.makeAnagram(a, b));

    }
}
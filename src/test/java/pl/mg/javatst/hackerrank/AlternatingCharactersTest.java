package pl.mg.javatst.hackerrank;

import org.junit.Assert;
import org.junit.Test;

public class AlternatingCharactersTest {

    @Test
    public void alternatingCharacters() {
        String sample = "AAAA";
        Assert.assertEquals(3, AlternatingCharacters.alternatingCharacters(sample));
    }
}
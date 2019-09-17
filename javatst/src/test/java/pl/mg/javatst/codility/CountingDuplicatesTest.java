package pl.mg.javatst.codility;

import org.junit.Assert;
import org.junit.Test;

public class CountingDuplicatesTest {

    @Test
    public void duplicateCount() {
        String duplicates = "indivisibility";
        Assert.assertEquals(1, CountingDuplicates.duplicateCount(duplicates));
    }
}
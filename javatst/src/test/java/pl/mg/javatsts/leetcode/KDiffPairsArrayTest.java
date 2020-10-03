package pl.mg.javatsts.leetcode;

import org.junit.Test;

import static org.junit.Assert.*;

public class KDiffPairsArrayTest {

    @Test
    public void findPairs() {
        KDiffPairsArray tested = new KDiffPairsArray();
        assertEquals(2, tested.findPairs(new int[]{3, 1, 4, 1, 5}, 2));
    }
}
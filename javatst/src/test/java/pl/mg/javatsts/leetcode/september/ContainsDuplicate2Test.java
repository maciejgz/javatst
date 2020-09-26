package pl.mg.javatsts.leetcode.september;

import org.junit.Test;

import static org.junit.Assert.*;

public class ContainsDuplicate2Test {

    @Test
    public void containsNearbyAlmostDuplicatePositiveTest() {
        ContainsDuplicate2 containsDuplicate2 = new ContainsDuplicate2();

        assertTrue(containsDuplicate2.containsNearbyAlmostDuplicateBruteForce(new int[]{1, 2, 3, 1}, 3, 0));
        assertFalse(containsDuplicate2.containsNearbyAlmostDuplicateBruteForce(new int[]{-2147483648, 2147483647}, 1, 1));
    }
}
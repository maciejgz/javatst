package pl.mg.javatsts.leetcode;

import org.junit.Test;

import static org.junit.Assert.*;

public class FirstMissingPositive30Test {

    @Test
    public void firstMissingPositive() {
        FirstMissingPositive30 testedClass = new FirstMissingPositive30();
        assertEquals(3, testedClass.firstMissingPositive(new int[]{1, 2, 0}));

    }
}
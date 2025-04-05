package pl.mg.javatst.leetcode;

import org.junit.Test;

import static org.junit.Assert.*;

public class TeemoAttacking26Test {

    @Test
    public void findPoisonedDuration() {
        TeemoAttacking26 teemoAttacking26 = new TeemoAttacking26();
        assertEquals(9, teemoAttacking26.findPoisonedDuration(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}, 1));
    }
}
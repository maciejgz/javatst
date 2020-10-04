package pl.mg.javatsts.leetcode;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class RemoveCoveredIntervals1288Test {

    @Test
    public void removeCoveredIntervals() {
        int[][] input = new int[3][2];

        input[0] = new int[]{1, 2};
        input[1] = new int[]{1, 4};
        input[2] = new int[]{3, 4};

        RemoveCoveredIntervals1288 tested = new RemoveCoveredIntervals1288();
        Assert.assertEquals(1, tested.removeCoveredIntervals(input));

        int[][] input2 = new int[10][2];

        input2[0] = new int[]{66672, 75156};
        input2[1] = new int[]{59890, 65654};
        input2[2] = new int[]{92950, 95965};
        input2[3] = new int[]{9103, 31953};
        input2[4] = new int[]{54869, 69855};
        input2[5] = new int[]{33272, 92693};
        input2[6] = new int[]{52631, 65356};
        input2[7] = new int[]{43332, 89722};
        input2[8] = new int[]{4218, 57729};
        input2[9] = new int[]{20993, 92876};
        Assert.assertEquals(3, tested.removeCoveredIntervals(input2));

    }
}
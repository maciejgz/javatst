package pl.mg.javatsts.leetcode;

import org.junit.Test;

import static org.junit.Assert.*;

public class RecentCounterTest {


    @Test
    public void testCounter() {
        RecentCounter counter = new RecentCounter();

        System.out.println(counter.ping(1));
        System.out.println(counter.ping(1));
        System.out.println(counter.ping(3002));
    }

}
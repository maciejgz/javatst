package pl.mg.javatst.codility;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PathFinder1Test {

    @Test
    public void sampleTests() {

        String a = ".W.\n" +
                ".W.\n" +
                "...",

                b = ".W.\n" +
                        ".W.\n" +
                        "W..",

                c = "......\n" +
                        "......\n" +
                        "......\n" +
                        "......\n" +
                        "......\n" +
                        "......",

                d = "......\n" +
                        "......\n" +
                        "......\n" +
                        "......\n" +
                        ".....W\n" +
                        "....W.";
        assertEquals(true, PathFinder1.pathFinder(a));
        assertEquals(false, PathFinder1.pathFinder(b));
        assertEquals(true, PathFinder1.pathFinder(c));
        assertEquals(false, PathFinder1.pathFinder(d));
    }
}


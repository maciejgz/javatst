package pl.mg.javatst.codility;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BracketsTest {

    @Test
    public void solution() {
        Brackets brackets = new Brackets();
        assertEquals(1, brackets.solution("[]"));
        assertEquals(0, brackets.solution("["));
        assertEquals(1, brackets.solution("(((())))"));
    }
}

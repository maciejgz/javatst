package pl.mg.javatst.codewars;

import junit.framework.TestCase;
import org.junit.Test;

public class UserTest extends TestCase {


    @Test
    public void testUser() {
        User user = new User();
        user.incProgress(5);
        assertEquals(7, user.rank);
        assertEquals(40, user.progress);
    }

}
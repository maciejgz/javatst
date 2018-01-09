package pl.mg.javatst.java8.lambda;

import org.junit.Assert;
import org.junit.Test;

public class Lambda8SampleTest {

    @Test
    public void lambdaSyntaxTest() {
        Assert.assertEquals(1, Lambda8Sample.lambdaSyntax().size());

    }


    @Test
    public void runnableLambdaTest() {
        Assert.assertEquals("result", Lambda8Sample.lambdaInCallable());
    }
}
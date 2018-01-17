package pl.mg.javatst.java8.lambda;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class Lambda8SampleTest {

    @Test
    public void lambdaSyntaxTest() {
        Assert.assertEquals(1, Lambda8Sample.lambdaSyntax().size());
    }

    @Test
    public void runnableLambdaTest() {
        Assert.assertEquals("result", Lambda8Sample.lambdaInCallable());
    }


    @Test
    public void functionInterfaceTest() {
        List<Integer> result = Lambda8Sample.map(Arrays.asList("lamba", "test", "another test"), (String s) -> s.length());
        Assert.assertEquals(new Integer(5), result.get(0));
    }
}
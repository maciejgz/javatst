package pl.mg.javatst.java8;

import org.junit.Before;
import org.junit.Test;
import pl.mg.javatst.java8.behaviourparametrization.BehaviourApple2;
import pl.mg.javatst.java8.behaviourparametrization.BehaviourParametrization2;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BehaviourParametrizationTest {


    private List<BehaviourApple2> apples = null;

    @Before
    public void prepareEnv() {
        apples = new ArrayList<>();

        BehaviourApple2 apple1 = new BehaviourApple2("red", 120);
        BehaviourApple2 apple2 = new BehaviourApple2("blue", 120);
        BehaviourApple2 apple3 = new BehaviourApple2("red", 80);
        BehaviourApple2 apple4 = new BehaviourApple2("white", 120);

        apples.add(apple1);
        apples.add(apple2);
        apples.add(apple3);
        apples.add(apple4);
    }

    @Test
    public void redAppleTestJava7() {
        assertEquals(2, BehaviourParametrization2.filter(apples, new BehaviourParametrization2.Predicate<BehaviourApple2>() {
            @Override
            public boolean test(BehaviourApple2 behaviourApple2) {
                return "red".equals(behaviourApple2.getColor());
            }
        }).size());
    }

    @Test
    public void redAppleTestJava8() {

        List<BehaviourApple2> result = BehaviourParametrization2.filter(apples,
                (BehaviourApple2 apple) -> "red".equals(apple.getColor()));

        assertEquals(2, result.size());
    }
}

package pl.mg.javatst.java8;

import org.junit.Before;
import org.junit.Test;
import pl.mg.javatst.java8.behaviourparametrization.BehaviourApple;
import pl.mg.javatst.java8.behaviourparametrization.BehaviourParametrization;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BehaviourParametrizationTest {


    private List<BehaviourApple> apples = null;

    @Before
    public void prepareEnv() {
        apples = new ArrayList<>();

        BehaviourApple apple1 = new BehaviourApple("red", 120);
        BehaviourApple apple2 = new BehaviourApple("blue", 120);
        BehaviourApple apple3 = new BehaviourApple("red", 80);
        BehaviourApple apple4 = new BehaviourApple("white", 120);

        apples.add(apple1);
        apples.add(apple2);
        apples.add(apple3);
        apples.add(apple4);
    }

    @Test
    public void redAppleTestJava7() {
        assertEquals(2, BehaviourParametrization.filter(apples, new BehaviourParametrization.Predicate<BehaviourApple>() {
            @Override
            public boolean test(BehaviourApple behaviourApple) {
                return "red".equals(behaviourApple.getColor());
            }
        }).size());
    }

    @Test
    public void redAppleTestJava8() {

        List<BehaviourApple> result = BehaviourParametrization.filter(apples,
                (BehaviourApple apple) -> "red".equals(apple.getColor()));

        assertEquals(2, result.size());
    }
}

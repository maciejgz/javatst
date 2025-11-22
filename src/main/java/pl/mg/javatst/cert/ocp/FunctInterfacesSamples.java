package pl.mg.javatst.cert.ocp;

import pl.mg.javatst.java8.behaviourparametrization.BehaviourParametrization;

import java.util.HashMap;
import java.util.Map;

public class FunctInterfacesSamples {

    public String sample = "";

    public static void main(String[] args) {
        doSmth(new FunctInterfacesSamples(), w -> w.sample.length() > 0);
        Map<String, String> sampleMap = new HashMap<>();

        sampleMap.merge("1", "1", (a, b) -> a + b);


        sampleMap.merge("1", "1", (a, b) -> a + b);
        sampleMap.merge("1", "1", (a, b) -> a + b + a.length());
        sampleMap.merge("1", "1", (a, b) -> a + b);

        FunctInterfacesSamples sample = new FunctInterfacesSamples();
        //sample.checkNullMethod(Objects.requireNonNull(null, "custom null message"));
        sample.checkNullMethod("");

    }

    private static void doSmth(FunctInterfacesSamples obj, BehaviourParametrization.Predicate<FunctInterfacesSamples> inputMethod) {
        System.out.println(inputMethod.test(obj));
    }

    private void checkNullMethod(String input) {
        assert !input.equalsIgnoreCase("");
        System.out.println(input);
    }
}




package pl.mg.javatst.cert.ocp;

import lombok.var;

import javax.annotation.Nonnull;
import java.util.function.LongUnaryOperator;

public class DefaultInterfaceImpl implements DefaultInterface {


    public static void main(String[] args) {
        DefaultInterfaceImpl impl = new DefaultInterfaceImpl();

        var testVal = "res";

        @Nonnull String sample;
        System.out.println(impl.afterDefault("re"));


        LongUnaryOperator lou = l -> l * 2;
        long l = lou.compose(lou).applyAsLong(3);
        System.out.println(l);

    }

    public String afterDefault(String addon) {


        return this.someDefaultMethod(addon);
    }
}

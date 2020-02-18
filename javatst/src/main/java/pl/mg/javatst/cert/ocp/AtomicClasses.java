package pl.mg.javatst.cert.ocp;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicClasses {

    public static void main(String[] args) {
        AtomicInteger integer = new AtomicInteger(11);

        System.out.println(integer.get());
        System.out.println(integer.addAndGet(120));
    }
}

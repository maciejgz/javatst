package pl.mg.javatst.util;

public interface Animal {

    public static final String NAME = "animal";

    static void staticRoar() {
        System.out.println("static roar");
    }

    public abstract void roar();

    default void roarByDefault() {
        System.out.println("animal roar");
    }

}

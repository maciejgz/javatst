package pl.mg.javatst.codewars;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * Solution works for Java 8 with default JVM config. Java 11 requires additional flags to be added to jvm.
 */
public class BegelSolver {

    public static void main(String[] args) {
        System.out.println(BegelSolver.getBagel().getValue() == 4);
    }

    public static Bagel getBagel() {
        try {
            Field f = Boolean.class.getField("TRUE");
            Field modifiers = Field.class.getDeclaredField("modifiers");
            modifiers.setAccessible(true);
            modifiers.setInt(f, f.getModifiers() & ~Modifier.FINAL);
            f.set(null, false);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return new Bagel();
    }


}



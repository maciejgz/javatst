package pl.mg.javatst.util;

public abstract class Mammal implements Animal {

    public int legs = 4;

    public abstract int howManyLegs();

    public int howManyWings() {
        return 0;
    }
}

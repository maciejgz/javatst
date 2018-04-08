package pl.mg.javatst.pattern.abstractfactory;

public class Red implements Color {
    @Override
    public void fill() {
        System.out.println("Inside Red::fill() method.");
    }
}

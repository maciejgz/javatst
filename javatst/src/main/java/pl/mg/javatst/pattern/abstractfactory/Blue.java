package pl.mg.javatst.pattern.abstractfactory;

public class Blue implements Color {

    @Override
    public void fill() {
        System.out.println("Inside blue::fill() method.");
    }
}

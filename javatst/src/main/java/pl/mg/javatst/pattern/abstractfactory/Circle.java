package pl.mg.javatst.pattern.abstractfactory;

public class Circle implements Shape {

    @Override
    public void draw() {
        System.out.println("draw circle");
    }
}

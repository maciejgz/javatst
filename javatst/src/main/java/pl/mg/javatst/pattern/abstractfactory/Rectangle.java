package pl.mg.javatst.pattern.abstractfactory;

public class Rectangle implements Shape {

    @Override
    public void draw() {
        System.out.println("draw rectangle");
    }
}

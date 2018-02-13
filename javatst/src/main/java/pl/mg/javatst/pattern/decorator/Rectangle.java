package pl.mg.javatst.pattern.decorator;

public class Rectangle implements Shape {
    @Override
    public void draw() {
        System.out.println("shape rectangle");
    }
}

package pl.mg.javatst.pattern.facade;

public class Rectangle implements Shape {
    @Override
    public void draw() {
        System.out.println("Rectangle");
    }
}

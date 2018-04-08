package pl.mg.javatst.pattern.prototype;

public class Rectangle extends Shape {

    public Rectangle() {
        type = "rectangle";
    }

    @Override
    void draw() {
        System.out.println("rectangle");
    }
}

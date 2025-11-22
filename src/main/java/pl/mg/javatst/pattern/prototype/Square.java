package pl.mg.javatst.pattern.prototype;

public class Square extends Shape {

    public Square() {
        this.type = "square";
    }

    @Override
    void draw() {
        System.out.println("square");
    }
}

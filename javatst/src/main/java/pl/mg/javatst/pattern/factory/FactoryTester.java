package pl.mg.javatst.pattern.factory;

public class FactoryTester {

    public static void main(String[] args) {
        Shape circle = ShapeFactory.build(ShapeType.CIRCLE);
        Shape rectangle = ShapeFactory.build(ShapeType.RECTANGLE);
        Shape triangle = ShapeFactory.build(ShapeType.TRIANGLE);

        circle.draw();
        rectangle.draw();
        triangle.draw();

    }
}

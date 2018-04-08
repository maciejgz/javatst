package pl.mg.javatst.pattern.factory;

public class ShapeFactory {

    public static Shape build(ShapeType type) {
        if (type == null) {
            return null;
        }

        switch (type) {
            case CIRCLE:
                return new CircleShape();
            case TRIANGLE:
                return new TriangleShape();
            case RECTANGLE:
                return new RectangleShape();
            default:
                return null;
        }
    }
}

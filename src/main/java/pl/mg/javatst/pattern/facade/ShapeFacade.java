package pl.mg.javatst.pattern.facade;

public class ShapeFacade {

    private Shape circle;
    private Shape rectangle;
    private Shape square;

    public ShapeFacade() {
        this.circle = new Circle();
        this.rectangle = new Rectangle();
        this.square = new Square();
    }

    public void drawCircle() {
        this.circle.draw();
    }

    public void drawRectangle() {
        this.rectangle.draw();
    }

    public void drawSquare() {
        this.square.draw();
    }
}

package pl.mg.javatst.pattern.bridge;


/**
 * uzywany do oddzielenia abstrakcji od implementacji.
 * Obiekt zawiera w sobie interfejs funkcyjny.
 */
public class BridgePatternDemo {

    public static void main(String[] args) {
        Shape redCircle = new Circle(100, 100, 10, new RedCircle());
        Shape greenCircle = new Circle(100, 100, 10, new GreenCircle());

        redCircle.draw();
        greenCircle.draw();
    }
}

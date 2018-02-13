package pl.mg.javatst.pattern.decorator;

/**
 * Dekorator pozwala dodac funkcjonalność istniejącego obiektu bez naruszania jego struktury.
 * Tworzony jest wrapper głównej klasy i dodanie funkcjonalnosci
 */
public class DecoratorPatternDemo {

    public static void main(String[] args) {

        Shape circle = new Circle();

        Shape redCircle = new RedShapeDecorator(new Circle());

        Shape redRectangle = new RedShapeDecorator(new Rectangle());
        System.out.println("Circle with normal border");
        circle.draw();

        System.out.println("\nCircle of red border");
        redCircle.draw();

        System.out.println("\nRectangle of red border");
        redRectangle.draw();
    }
}

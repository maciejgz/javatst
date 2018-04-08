package pl.mg.javatst.pattern.facade;

/**
 * Fasada to w pewnym sensie prosty interface złozonego obiektu.
 * Dostosowanie do klienta w celu ułatwienia korzystania z systemu.
 */
public class FacadePatternDemo {

    public static void main(String[] args) {
        ShapeFacade facade = new ShapeFacade();
        facade.drawCircle();
        facade.drawRectangle();
        facade.drawSquare();
    }
}

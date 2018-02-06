package pl.mg.javatst.pattern.prototype;

/**
 * Prototype jest używany do tworzenia obiektu z juz
 * istniejącego obiektu
 */
public class PrototypeTest {

    public static void main(String[] args) {

        ShapeCache.loadCache();
        Shape clonedShape = ShapeCache.getShape("1");
        System.out.println("Shape: " + clonedShape.getType());


        Shape clonedShape2 = ShapeCache.getShape("2");
        System.out.println("Shape: " + clonedShape2.getType());

    }
}

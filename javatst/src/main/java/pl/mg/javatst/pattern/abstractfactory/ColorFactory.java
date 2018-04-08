package pl.mg.javatst.pattern.abstractfactory;

public class ColorFactory extends AbstractFactory {
    @Override
    Color getColor(String color) {
        if (org.apache.commons.lang3.StringUtils.isBlank(color)) {
            return null;
        }

        if (color.equalsIgnoreCase("RED")) {
            return new Red();
        }

        if (color.equalsIgnoreCase("BLUE")) {
            return new Blue();
        }
        return null;
    }

    @Override
    Shape getShape(String shape) {
        return null;
    }
}

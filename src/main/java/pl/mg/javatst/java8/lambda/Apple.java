package pl.mg.javatst.java8.lambda;

public class Apple {

    private String color;

    public Apple(String color, int weight) {
        this.color = color;
        this.weight = weight;
    }

    public Apple() {
    }

    private int weight;

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}

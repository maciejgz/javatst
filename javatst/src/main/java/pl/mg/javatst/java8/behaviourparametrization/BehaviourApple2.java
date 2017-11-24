package pl.mg.javatst.java8.behaviourparametrization;

public class BehaviourApple2 {

    private String color;

    public BehaviourApple2(String color, int weight) {
        this.color = color;
        this.weight = weight;
    }

    public BehaviourApple2() {
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

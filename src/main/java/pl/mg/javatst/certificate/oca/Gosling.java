package pl.mg.javatst.certificate.oca;

public class Gosling extends Bird {
    public static void main(String[] args) {
        Gosling gos = new Gosling();
        gos.swim();

    }

    public void swim() {
        floatInWater();
        System.out.println(text);
    }
}

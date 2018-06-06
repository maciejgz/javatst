package pl.mg.javatst.certificate.oca.bird;

public abstract class AbstractBird {

  public static void main(String[] args) {

    Brubel brubel = new Brubel();


    brubel.fly();
    brubel.eat();
  }

  public void fly() {
    System.out.println("Abstract fly");
  }

  protected void eat() {
    System.out.println("eat");
  }
}

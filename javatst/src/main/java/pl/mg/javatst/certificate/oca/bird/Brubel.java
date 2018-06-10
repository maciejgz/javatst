package pl.mg.javatst.certificate.oca.bird;

import java.time.LocalDate;

public class Brubel extends AbstractBird {

  public static void main(String[] args) {

    System.out.println("main");
    StringBuilder b1 = new StringBuilder("snorkler");
    StringBuilder b2 = new StringBuilder("yoodler");

    b1.replace(3, 4, b2.substring(4)).append(b2.append(false));

    System.out.println(b1.toString());
    System.out.println(b2.toString());
  }

  public void fly() {
    System.out.println("brubel flight");
  }

  public void throwExceptionMethodTest() throws BrubelException {

    LocalDate date;
  }
}

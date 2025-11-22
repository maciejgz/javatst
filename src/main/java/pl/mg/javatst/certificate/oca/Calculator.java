package pl.mg.javatst.certificate.oca;

abstract class Calculator {
  public static void main(String[] args) {
    System.out.println("calculating");
    Calculator x = null;
    x.calculate();


  }

  abstract void calculate();
}

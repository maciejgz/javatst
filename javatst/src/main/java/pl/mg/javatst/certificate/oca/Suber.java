package pl.mg.javatst.certificate.oca;

public class Suber {

  public static void main(String[] args) {
    Super s1 = new Super(); // 1
    Sub s2 = new Sub(); // 2
    s1 = (Super) s2; // 3

  }
}

class Super {}

class Sub extends Super {}

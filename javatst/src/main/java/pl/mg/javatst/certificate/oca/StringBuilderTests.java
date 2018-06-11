package pl.mg.javatst.certificate.oca;

public class StringBuilderTests {

  public static void main(String[] args) {

    StringBuilder builder = new StringBuilder();

    Integer i = new Integer(1);
    Long m = new Long(1);
    if (i.equals(m)) System.out.println("equal");
    else System.out.println("not equal");

  }
}

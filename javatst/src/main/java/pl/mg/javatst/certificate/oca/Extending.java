package pl.mg.javatst.certificate.oca;

public class Extending {
  public static void main(String[] args) {
    Base b = new Base2();
    System.out.println(b.getValue()); // 3

    int i = 0;
    boolean bool1 = true;
    boolean bool2 = false;
    boolean bool = false;
    bool = (bool2 & method1(i++));
    bool = (bool2 && method1(i++));
    bool = (bool1 | method1(i++));
    bool = (bool1 || method1(i++));
    System.out.println(i);
  }

  public static boolean method1(int i) {
    return i > 0 ? true : false;
  }
}

class Base {
  public short getValue() {
    return 1;
  } // 1
}

class Base2 extends Base {
  // tu musi być ten sam typ
  public short getValue() {
    return 2;
  } // 2
}

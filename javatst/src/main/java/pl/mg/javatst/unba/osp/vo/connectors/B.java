package pl.mg.javatst.unba.osp.vo.connectors;

class TestClass {
    static int si = 10;

    public TestClass() {
        System.out.println(this);
    }

    public static void main(String args[]) {
        new TestClass();
    }

    public String toString() {
        return "TestClass.si = " + this.si;
    }
}
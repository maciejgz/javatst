package pl.mg.javatst.cert.ocp;

public class WhizPT2Qn {
    static void f(Object o ) {
        System.out.println("object");
    }

    static void f(String s) {
        System.out.println("static");
    }

    static void f(Integer i) {
        System.out.println("integer");
    }

    static <T> void g(T t) {
        f(t);
    }

    public static void main(String[] args) {
        g("123");
    }
}

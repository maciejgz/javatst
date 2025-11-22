package pl.mg.javatst.certificate.oca;

public class Order {
    static String result = "";

    static {
        result += "u";

    }

    {
        result += "c";
    }

    {
        result += "r";
    }

    public static void main(String[] args) {
        System.out.print(Order.result + " ");
        System.out.print(Order.result + " ");
        new Order();
        new Order();
        System.out.println(Order.result + " ");
    }
}

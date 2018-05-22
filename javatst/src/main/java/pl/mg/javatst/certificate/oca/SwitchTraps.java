package pl.mg.javatst.certificate.oca;

import java.util.Collections;

public class SwitchTraps {

    public static void main(String[] args) {


        long a = 40L;
        long b = 50L;
        long res = a + b;
        System.out.println(res);



        int value = 12;
        switch (value) {
            case 12:
                System.out.println("12");
                break;
            case 15:
                System.out.println("15");
            default:
                System.out.println("24");
        }
    }
}

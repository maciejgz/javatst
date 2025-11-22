package pl.mg.javatst.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

public class TestClass {
    public static void main(String[] args) throws Exception {

        Animal orka = new Orka();

        System.out.println(((Orka) orka).howManyLegs());
        System.out.println(((Orka) orka).legs);

        System.out.println(((Mammal) orka).howManyLegs());
        System.out.println(((Mammal) orka).legs);

        for (int i = 0; i < 10; i++)
            System.out.println("sample");

        int x = 0;


        if (false) {
            x = 3;
        }
        do {
            x = 3;
        } while (false);


        int index = 1; String[] strArr = new String[5]; String   myStr  = strArr[index]; System.out.println(myStr);


        System.out.println(Boolean.parseBoolean("TrUe") == new Boolean(null));
        System.out.println(new Boolean("TrUe") == new Boolean(true));
        System.out.println(new Boolean("no") == false);

        Collection<String> c = new ArrayList<>();

        int w = 10;
        do{
            w--;
            System.out.println(w);  // 1
        }while(w<10);
    }



    public void myMethod() throws Exception {
        yourMethod();
    }

    public void yourMethod() throws Exception {
        throw new Exception();
    }
}

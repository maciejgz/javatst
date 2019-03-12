package pl.mg.javatst.util;

class base3 {
    public static void main(String args[]) {
        byte b = -128 ;
        int i = b ;
        b = (byte) i;
        System.out.println(i+" "+b);

    }

    public static boolean method1(int i) {
        return i > 0 ? true : false;
    }
}
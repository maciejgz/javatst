package pl.mg.javatst.certificate.oca;

public class TestClass {
    static String str;

    public static void main(String[] args) {
        String myStr = "good";
        char[] myCharArr = {'g', 'o', 'o', 'd'};
        String newStr = "";
        for (char ch : myCharArr) {
            newStr = newStr + ch;
        }
        boolean b1 = newStr == myStr;
        boolean b2 = newStr.equals(myStr);
        System.out.println(b1 + " " + b2);
    }


}
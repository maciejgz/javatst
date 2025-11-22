package pl.mg.javatst.util;

public class SoccerTest {
    public static void main (String args []){
        int sum = 0;
        for (int i = 0, j = 10; sum > 20; ++i, --j)      // 1
        {
            sum = sum+ i + j;
        }
        System.out.println("Sum = " + sum);
    }
    public static boolean method1(String str){
        System.out.println(str);
        return true;
    }
}

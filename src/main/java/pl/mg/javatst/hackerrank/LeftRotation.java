package pl.mg.javatst.hackerrank;

public class LeftRotation {

    public static void main(String[] args) {
        int[] input = new int[5];
        input[0] = 0;
        input[1] = 1;
        input[2] = 2;
        input[3] = 3;
        input[4] = 4;


        int[] ints = rotLeft(input, 2);
        for (int anInt : ints) {
            System.out.println(anInt);
        }

    }

    static int[] rotLeft(int[] a, int d) {
        int[] result = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            int pointer = i + d;
            if (pointer > (a.length - 1)) {
                pointer = (i + d) % a.length;
            }
            result[i] = a[pointer];
        }
        return result;
    }


}

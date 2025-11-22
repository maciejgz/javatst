package pl.mg.javatst.hackerrank;

import java.util.Arrays;

/**
 * napisz metode znajdującą w tablicy intów drugi w kolejności największy element
 */
public class SecondMax {

    public static void main(String[] args) {
        int[] input = new int[]{1, 3, 2, 0, 5};
        System.out.println(findSecondElement(input));
    }

    public static int findSecondElement(int[] input) {
        Arrays.sort(input);
        return input[input.length - 2];
    }
}

package pl.mg.javatst.codewars;

public class Kata {

    public static void main(String[] args) {
        System.out.println(findEvenIndex(new int[]{1, 100, 50, -51, 1, 1}));
        System.out.println(findEvenIndex(new int[]{1, 2, 3, 4, 5, 6}));
        System.out.println(findEvenIndex(new int[]{20, 10, 30, 10, 10, 15, 35}));
        System.out.println(findEvenIndex(new int[]{-8505, -5130, 1926, -9026}));
        System.out.println(findEvenIndex(new int[]{2824, 1774, -1490, -9084, -9696, 23094}));
        System.out.println(findEvenIndex(new int[]{4, 5, 6, 7, 8, 9, 10, 9, 8, 7, 6, 5, 4}));
    }

    public static int findEvenIndex(int[] arr) {
        if (arr.length == 0) {
            return 0;
        }
        for (int i = 0; i < arr.length; i++) {
            if (sum(arr, 0, i) == sum(arr, i, arr.length - 1)) {
                return i;
            }
        }

        return -1;
    }

    private static int sum(int[] arr, int start, int stop) {
        int sum = 0;
        for (int i = start; i <= stop; i++) {
            sum += arr[i];
        }

        return sum;

    }

}

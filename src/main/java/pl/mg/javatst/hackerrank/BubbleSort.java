package pl.mg.javatst.hackerrank;

/**
 * https://www.hackerrank.com/challenges/ctci-bubble-sort
 * 30 (max result)
 */
public class BubbleSort {

    static void countSwaps(int[] a) {
        int numOfSwaps = 0;
        for (int i = a.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                // Swap adjacent elements if they are in decreasing order
                if (a[j] > a[j + 1]) {
                    int big = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = big;
                    numOfSwaps++;
                }
            }
        }
        System.out.println("Array is sorted in " + numOfSwaps + " swaps.");
        System.out.println("First Element: " + a[0]);
        System.out.println("Last Element: " + a[a.length - 1]);
    }

    public static void main(String[] args) {
        int[] arr = {6, 4, 1};
        countSwaps(arr);
    }

}

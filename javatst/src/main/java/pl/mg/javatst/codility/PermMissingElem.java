package pl.mg.javatst.codility;

/**
 * https://app.codility.com/programmers/lessons/3-time_complexity/
 * <p>
 * A zero-indexed array A consisting of N different integers is given.
 * The array contains integers in the range [1..(N + 1)], which means
 * that exactly one element is missing.
 * <p>
 * Your goal is to find that missing element.
 * <p>
 * Write a function:
 * <p>
 * class Solution { public int solution(int[] A); }
 * <p>
 * that, given a zero-indexed array A, returns the value of the missing element.
 * <p>
 * For example, given array A such that:
 * <p>
 * A[0] = 2
 * A[1] = 3
 * A[2] = 1
 * A[3] = 5
 * the function should return 4, as it is the missing element.
 * <p>
 * Assume that:
 * <p>
 * N is an integer within the range [0..100,000];
 * the elements of A are all distinct;
 * each element of array A is an integer within the range [1..(N + 1)].
 * Complexity:
 * <p>
 * expected worst-case time complexity is O(N);
 * expected worst-case space complexity is O(1), beyond input storage
 * (not counting the storage required for input arguments).
 * <p>
 * 100%
 */
public class PermMissingElem {

    public static void main(String[] args) {
        PermMissingElem permMissingElem = new PermMissingElem();
        permMissingElem.solution(new int[]{1, 2, 3});
    }

    public int solution(int[] A) {
        int shouldBeSum = 0;
        int realSum = 0;
        int i = 0;
        while (i < A.length) {
            realSum += A[i];
            shouldBeSum += i + 1;
            i++;
        }
        shouldBeSum += i + 1;
        return shouldBeSum - realSum;
    }
}

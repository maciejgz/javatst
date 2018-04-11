package pl.mg.javatst.codility;

import java.util.HashSet;

/**
 * https://app.codility.com/programmers/lessons/4-counting_elements/
 * Task description
 * A small frog wants to get to the other side of a river. The frog is
 * initially located on one bank of the river (position 0) and wants to
 * get to the opposite bank (position X+1). Leaves fall from a tree onto the surface of the river.
 * <p>
 * You are given a zero-indexed array A consisting of N integers representing
 * the falling leaves. A[K] represents the position where one leaf falls at time K, measured in seconds.
 * <p>
 * The goal is to find the earliest time when the frog can jump to the other
 * side of the river. The frog can cross only when leaves appear at every
 * position across the river from 1 to X (that is, we want to find the
 * earliest moment when all the positions from 1 to X are covered by leaves).
 * You may assume that the speed of the current in the river is negligibly small,
 * i.e. the leaves do not change their positions once they fall in the river.
 * <p>
 * For example, you are given integer X = 5 and array A such that:
 * <p>
 * A[0] = 1
 * A[1] = 3
 * A[2] = 1
 * A[3] = 4
 * A[4] = 2
 * A[5] = 3
 * A[6] = 5
 * A[7] = 4
 * In second 6, a leaf falls into position 5. This is the earliest time when
 * leaves appear in every position across the river.
 * <p>
 * Write a function:
 * <p>
 * class Solution { public int solution(int X, int[] A); }
 * <p>
 * that, given a non-empty zero-indexed array A consisting of N integers and
 * integer X, returns the earliest time when the frog can jump to the other side of the river.
 * <p>
 * If the frog is never able to jump to the other side of the river,
 * the function should return −1.
 * <p>
 * For example, given X = 5 and array A such that:
 * <p>
 * A[0] = 1
 * A[1] = 3
 * A[2] = 1
 * A[3] = 4
 * A[4] = 2
 * A[5] = 3
 * A[6] = 5
 * A[7] = 4
 * the function should return 6, as explained above.
 * <p>
 * Assume that:
 * <p>
 * N and X are integers within the range [1..100,000];
 * each element of array A is an integer within the range [1..X].
 * Complexity:
 * <p>
 * expected worst-case time complexity is O(N);
 * expected worst-case space complexity is O(X), beyond input storage
 * (not counting the storage required for input arguments).
 */
public class FrogRiverOne {

    public static void main(String[] args) {
        FrogRiverOne frogRiverOne = new FrogRiverOne();
        frogRiverOne.solution(5, new int[]{1, 3, 1, 4, 2, 3, 5, 4});
    }


    public int solution(int X, int[] A) {
        int result = 0;

        HashSet<Integer> places = new HashSet<>();

        for (int i = 0; i < A.length; i++) {
            if (A[i] <= X) {
                places.add(A[i]);
            }
            if (places.size() == X) {
                result = i - 1;
            }
        }
        if (A.length == 1) {
            result = -1;
        }
        if (result < 0 || result < X) {
            result = -1;
        }
        return result;
    }
}

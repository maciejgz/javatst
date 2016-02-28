package pl.mg.javatst.codility;

import java.util.ArrayList;

/**
 * A small frog wants to get to the other side of a river. The frog is currently located at position 0, and wants to get to position X.
 * Leaves fall from a tree onto the surface of the river.
 * <p/>
 * <p/>
 * You are given a non-empty zero-indexed array A consisting of N integers representing the falling leaves.
 * A[K] represents the position where one leaf falls at time K, measured in seconds.
 * <p/>
 * The goal is to find the earliest time when the frog can jump to the other side of the river.
 * The frog can cross only when leaves appear at every position across the river from 1 to X.
 * You may assume that the speed of the current in the river is negligibly small, i.e. the leaves do not change their positions once they fall in the river.
 * <p/>
 * For example, you are given integer X = 5 and array A such that:
 * <p/>
 * A[0] = 1
 * A[1] = 3
 * A[2] = 1
 * A[3] = 4
 * A[4] = 2
 * A[5] = 3
 * A[6] = 5
 * A[7] = 4
 * In second 6, a leaf falls into position 5. This is the earliest time when leaves appear in every position across the river.
 * <p/>
 * Write a function:
 * <p/>
 * class Solution { public int solution(int X, int[] A); }
 * <p/>
 * that, given a non-empty zero-indexed array A consisting of N integers and integer X, returns the earliest time when the frog can jump to the other side of the river.
 * <p/>
 * If the frog is never able to jump to the other side of the river, the function should return −1.
 * <p/>
 * For example, given X = 5 and array A such that:
 * <p/>
 * A[0] = 1
 * A[1] = 3
 * A[2] = 1
 * A[3] = 4
 * A[4] = 2
 * A[5] = 3
 * A[6] = 5
 * A[7] = 4
 * the function should return 6, as explained above.
 * <p/>
 * Assume that:
 * <p/>
 * N and X are integers within the range [1..100,000];
 * each element of array A is an integer within the range [1..X].
 * Complexity:
 * <p/>
 * expected worst-case time complexity is O(N);
 * expected worst-case space complexity is O(X), beyond input storage (not counting the storage required for input arguments).
 * Elements of input arrays can be modified.
 * Created by Maiej on 2015-03-10.
 * 
 * TODO nieskoczone
 * 
 */
public class FrogRiverOneL2 {

    public int solution(int X, int[] A) {

        ArrayList<Integer> times = new ArrayList<>(A.length);

        if (X < 0) {
            return -1;
        }
        ArrayList<Integer> steps = new ArrayList<>();
        int timeIndex = 0;
        while (steps.size() < X && A.length < timeIndex) {
            if (!steps.contains(A[timeIndex]-1)) {
                steps.add(A[timeIndex]-1, timeIndex);
            }
            timeIndex++;
        }

        if(steps.size()<X){
            return -1;
        }

        return timeIndex--;
    }


}

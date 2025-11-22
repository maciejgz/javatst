package pl.mg.javatst.codility;

import java.util.HashSet;

/**
 * https://app.codility.com/programmers/lessons/4-counting_elements/
 * 72%
 */
public class FrogRiverOne {

    public static void main(String[] args) {
        FrogRiverOne frogRiverOne = new FrogRiverOne();
        System.out.println(frogRiverOne.solution(5, new int[]{1, 3, 1, 4, 2, 3, 5, 4}));
        System.out.println(frogRiverOne.solution(1, new int[]{1}));
    }


    public int solution(int X, int[] A) {
        int result = 0;

        HashSet<Integer> places = new HashSet<>();

        for (int i = 0; i < A.length; i++) {
            if (A[i] <= X) {
                places.add(A[i]);
            }
            if (places.size() == X) {
                result = i;
                break;
            }
        }
        if (A.length == 1) {
            result = 0;
        }
        if (result < 0) {
            result = -1;
        }
        return result;
    }
}

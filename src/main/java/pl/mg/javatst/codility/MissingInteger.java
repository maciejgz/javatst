package pl.mg.javatst.codility;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

/**
 * 100%
 */
public class MissingInteger {

    public static void main(String[] args) {
        MissingInteger missingInteger = new MissingInteger();
        System.out.println(missingInteger.solution(new int[]{4, 5, 6, 2}));
    }

    public int solution(int[] A) {
        int result = 0;
        HashSet<Integer> list = new HashSet<>();

        for (int i = 0; i < A.length; i++) {
            if (A[i] > 0) {
                list.add(A[i]);
            }
        }
        List<Integer> sortedList = new ArrayList(list);
        Collections.sort(sortedList);
        for (int j = 0; j < sortedList.size(); j++) {
            if (sortedList.get(j) != (j + 1)) {
                result = j + 1;
                break;
            }
        }

        if (sortedList.size() == 0) {
            result = 1;
        }
        if (result == 0) {
            result = sortedList.size() + 1;
        }
        return result;
    }


}

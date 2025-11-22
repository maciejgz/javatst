package pl.mg.javatst.codility;

/**
 * https://app.codility.com/programmers/lessons/3-time_complexity/
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

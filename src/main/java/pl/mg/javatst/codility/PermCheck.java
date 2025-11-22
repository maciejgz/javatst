package pl.mg.javatst.codility;

/**
 * 75% - można poprawić walidację powtórzeń
 */
public class PermCheck {

    public static void main(String[] args) {
        PermCheck permCheckL2Done = new PermCheck();
        System.out.println(permCheckL2Done.solution(new int[]{4, 1, 3}));
    }

    public int solution(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        if (A.length == 1 && A[0] == 1) {
            return 1;
        } else if (A.length == 1) {
            return 0;
        }

        int isPermutation = 1;
        int sum = 0;
        int shouldBeSum = 0;
        for (int i = 0; i < A.length; i++) {
            sum += A[i];
            shouldBeSum += i + 1;
        }
        if (shouldBeSum != sum) {
            isPermutation = 0;
        }
        return isPermutation;
    }
}

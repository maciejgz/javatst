package pl.mg.javatst.codility;

/**
 * 100%
 */
public class PassingCars {

    public static void main(String[] args) {
        PassingCars passingCars = new PassingCars();
        System.out.println(passingCars.solution(new int[]{0, 1, 0, 1, 1}));
    }

    public int solution(int[] A) {
        int result = 0;
        int eastCars = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] == 0) {
                eastCars++;
            }
            if (A[i] == 1) {
                result += eastCars;
            }

            if (result > 1000000000) {
                result = -1;
                break;
            }
        }
        return result;
    }

}

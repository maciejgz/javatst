package pl.mg.javatst.codility;

/**
 * 100%
 */
public class TapeEquilibrum {


    public static void main(String[] args) {
        TapeEquilibrum equilibrum = new TapeEquilibrum();
        System.out.println(equilibrum.solution(new int[]{3, 1, 2, 4, 3}));
    }

    public int solution(int[] A) {
        int sumRight = 0;
        for (int i = 1; i < A.length; i++) {
            sumRight += A[i];
        }
        int sumLeft = A[0];
        int minimumAnswer = Math.abs(sumLeft - sumRight);
        int answer;
        for (int i = 1; i < A.length; i++) {
            answer = Math.abs(sumLeft - sumRight);
            if (answer < minimumAnswer) {
                minimumAnswer = answer;
            }
            sumLeft += A[i];
            sumRight -= A[i];
        }
        return minimumAnswer;
    }
}

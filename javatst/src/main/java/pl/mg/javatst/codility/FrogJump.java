package pl.mg.javatst.codility;

/**
 * https://app.codility.com/programmers/lessons/3-time_complexity/
 * 100%
 */
public class FrogJump {

    public static void main(String[] args) {
        FrogJump frogRiverOneL2 = new FrogJump();
        System.out.println(frogRiverOneL2.solution(10, 85, 30));
    }

    public int solution(int X, int Y, int D) {

        int result = (int) Math.ceil(new Double(Y - X) / new Double(D));
        return result;

    }


}

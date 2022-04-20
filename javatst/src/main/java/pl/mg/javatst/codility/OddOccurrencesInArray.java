package pl.mg.javatst.codility;

/**
 * https://app.codility.com/programmers/lessons/2-arrays/
 *
 * 100%
 *
 * @author Maciej Gzik
 */
public class OddOccurrencesInArray {
    public static void main(String[] args) {

    }

    public int solution(int[] A) {
        int unpaired = 0;
        for (int i : A) {
            //bitwise exclusive OR
            unpaired ^= i;
        }

        return unpaired;
    }
}
package pl.mg.javatst.codility;


/**
 * https://app.codility.com/programmers/lessons/1-iterations/binary_gap/
 *
 * 100%
 *
 * @author Maciej Gzik
 */
public class BinaryGap {

    public static void main(String[] args) {
        BinaryGap gap = new BinaryGap();
        System.out.println(gap.solution(1000));
    }


    public int solution(int N) {
        String binary = Integer.toBinaryString(N);

        char[] chars = binary.toCharArray();
        int maxLength = 0;
        int length = 0;
        boolean started = false;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '1' && !started) {
                started = true;
            }
            if (chars[i] == '1' && started) {
                started = true;
                if (maxLength < length) {
                    maxLength = length;
                }
                length = 0;
            }
            if (chars[i] == '0' && started) {
                length++;
            }
        }
        return maxLength;
    }

}

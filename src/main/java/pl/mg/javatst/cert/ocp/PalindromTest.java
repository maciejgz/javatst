package pl.mg.javatst.cert.ocp;

public class PalindromTest {

    public static void main(String[] args) {

        char[] correct = {'a', 'b', 'a'};
        char[] correct2 = {'a', 'b', 'b', 'a'};
        char[] incorrect = {'a', 'c', 'b', 'a'};
        char[] incorrect2 = {'a', 'b', 'c'};
        System.out.println(isPalindrom(correct));
        System.out.println(isPalindrom(correct2));
        System.out.println(isPalindrom(incorrect));
        System.out.println(isPalindrom(incorrect2));
    }

    public static boolean isPalindrom(char[] input) {
        boolean result = true;
        for (int i = 0; i < Math.abs(input.length / 2); i++) {
            if (input[i] != input[input.length - 1 - i]) {
                result = false;
                break;
            }
        }
        return result;
    }
}

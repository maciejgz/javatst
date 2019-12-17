package pl.mg.javatst.crackingcodinginterview;

/**
 * Write recursive multiply without * operation.
 */
public class RecursiveMultiply {

    public static void main(String[] args) {
        RecursiveMultiply recursiveMultiply = new RecursiveMultiply();
        System.out.println(recursiveMultiply.multiply(1, 2));
        System.out.println(recursiveMultiply.multiply(-1, 2));
        System.out.println(recursiveMultiply.multiply(-3, 3));
        System.out.println(recursiveMultiply.multiply(-3, 0));


        System.out.println(recursiveMultiply.multipleRec(-3, 3));
        System.out.println(recursiveMultiply.multipleRec(-3, 3));
    }


    public int multiply(int a, int b) {
        int result = 0;
        for (int i = 0; i < b; i++) {
            result += Math.abs(a);
        }
        if (a < 0 && b % 2 != 0) {
            result = -result;
        }
        return result;
    }

    public int multipleRec(int a, int b) {
        int result = 0;
        int smaller = Math.min(a, b);
        int bigger = Math.max(a, b);
        result = multiplyRecursively(bigger, 0, smaller);
        if (a < 0 && b % 2 != 0) {
            result = -result;
        }
        return result;
    }

    public int multiplyRecursively(int value, int sum, int counter) {
        if (counter > 0) {
            counter--;
            return multiplyRecursively(value, sum + Math.abs(value), counter);
        } else return sum;
    }


}

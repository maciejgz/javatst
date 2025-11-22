package pl.mg.javatst.hackerrank;

public class StringConstruction {

    public static void main(String[] args) {
        int abab = stringConstruction("abab");
        System.out.println(abab);
    }

    static int stringConstruction(String s) {
        int result = 0;

        StringBuilder prefix = new StringBuilder();

        if (s == null || s.equalsIgnoreCase("")) {
            return 0;
        }

        for (int i = 0; i < s.length() - 1; i++) {
            prefix.append(s.charAt(i));
            result++;
            for (int j = s.length() - 1; j > i; j--) {
                if (prefix.toString().contains(s.substring(i+1, j))) {
                    prefix.append(s, i+1, j + 1);
                    break;
                }
            }

        }
        return result;
    }

}

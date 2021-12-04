package pl.mg.javatst.adventofcode;

import java.io.IOException;
import java.util.List;

public class BinaryDiagnostic3 {

    public static void main(String[] args) {
        try {
            List<String> input = FileUtil.readLines("adventofcode//3.txt");
            Long result = calcGammaRate(input);
            System.out.println("gamma: " + result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Long calcGammaRate(List<String> input) {
        int[] ones = new int[input.get(0).length()];
        for (String word : input) {
            char[] chars = word.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                if (chars[i] == '1') {
                    ones[i]++;
                }
            }
        }

        int[] gamma = new int[input.get(0).length()];
        int[] epsilon = new int[input.get(0).length()];

        for (int i = 0; i < ones.length; i++) {
            if (ones[i] > (input.size() / 2)) {
                gamma[i] = 1;
            } else {
                epsilon[i] = 1;
            }
        }
        String gammaString = "";
        String epsilonString = "";
        for (int i : gamma) {
            gammaString += Integer.toBinaryString(i);
        }
        for (int i : epsilon) {
            epsilonString += Integer.toBinaryString(i);
        }

        System.out.println(Integer.parseInt(gammaString, 2));
        System.out.println(Integer.parseInt(epsilonString, 2));
        long i = Long.parseLong(gammaString, 2) * Long.parseLong(epsilonString, 2);
        System.out.println("result: " + i);
        return i;
    }

}

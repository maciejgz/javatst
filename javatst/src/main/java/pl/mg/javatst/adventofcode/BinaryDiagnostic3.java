package pl.mg.javatst.adventofcode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BinaryDiagnostic3 {

    public static void main(String[] args) {
        try {
            List<String> input = FileUtil.readLines("adventofcode//3.txt");
            Long result = calcGammaRate(input);
            System.out.println("gamma: " + result);
            long oxygen = oxygenGenerator(input);
            long carbon = carbonGenerator(input);
            System.out.println("exycarbon: " + oxygen * carbon);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int mostPopularBit(int index, List<String> input) {
        int ones = 0;
        for (String word : input) {
            char[] chars = word.toCharArray();
            if (chars[index] == '1') {
                ones++;
            }
        }
        if ((double) ones >= ((double) input.size() / 2.0)) {
            return 1;
        } else {
            return 0;
        }
    }


    private static long oxygenGenerator(List<String> input) {
        List<String> result = new ArrayList<>(input);
        for (int i = 0; i < input.get(0).length(); i++) {
            int mostPopular = mostPopularBit(i, result);
            System.out.println("mostPopular position: " + i + " is " + mostPopular);
            Iterator<String> iter = result.iterator();
            while (iter.hasNext()) {
                String s = iter.next();
                if (Integer.valueOf(String.valueOf(s.toCharArray()[i])) != mostPopular) {
                    System.out.println("remove: " + s);
                    iter.remove();
                    if (result.size() == 1) {
                        break;
                    }
                }
            }
            if (result.size() == 1) {
                break;
            }
        }
        long resultValue = Long.parseLong(result.get(0), 2);
        System.out.println("oxygen result: " + resultValue + " left: " + result.get(0));
        return resultValue;
    }

    private static long carbonGenerator(List<String> input) {
        System.out.println("------------carbon--------------");
        List<String> result = new ArrayList<>(input);
        for (int i = 0; i < input.get(0).length(); i++) {
            int mostPopular = mostPopularBit(i, result);
            System.out.println("mostPopular position: " + i + " is " + mostPopular);
            Iterator<String> iter = result.iterator();
            while (iter.hasNext()) {
                String s = iter.next();
                if (Integer.valueOf(String.valueOf(s.toCharArray()[i])) == mostPopular && result.size() > 1) {
                    iter.remove();
                }
            }
        }
        long resultValue = Long.parseLong(result.get(0), 2);
        System.out.println("carbon resulkt: " + resultValue);
        return resultValue;
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

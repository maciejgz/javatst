package pl.mg.javatst.adventofcode2025;

import lombok.extern.slf4j.Slf4j;
import pl.mg.javatst.adventofcode2024.Utils;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Day07Laboratories {

    private static final Character BEAN_CHAR = '|';

    static void main() {
        List<String> lines = Utils.readLines("src/main/resources/adventofcode2025/day07_input.txt");

        int splits = 0;

        List<StringBuilder> beans = new ArrayList<>();
        for (String line : lines) {
            beans.add(new StringBuilder(line));
        }


        for (int i = 0; i < beans.size(); i++) {
            for (int j = 0; j < beans.get(i).length(); j++) {
                //S
                if (beans.get(i).charAt(j) == 'S') {
                    beans.get(i + 1).setCharAt(j, BEAN_CHAR);
                }

                //^
                if (beans.get(i).charAt(j) == '^' && beans.get(i - 1).charAt(j) == BEAN_CHAR) {
                    splits++;
                    if (j != 0) {
                        beans.get(i).setCharAt(j - 1, BEAN_CHAR);
                        if (i != (beans.size() - 1) && beans.get(i + 1).charAt(j - 1) == '.') {
                            beans.get(i + 1).setCharAt(j - 1, BEAN_CHAR);
                        }
                    }
                    if (j != (beans.get(i).length() - 1)) {
                        beans.get(i).setCharAt(j + 1, BEAN_CHAR);
                    }
                }

                //|
                if (beans.get(i).charAt(j) == BEAN_CHAR) {
                    if (i != (beans.size() - 1) && beans.get(i + 1).charAt(j) == '.') {
                        beans.get(i + 1).setCharAt(j, BEAN_CHAR);
                    }
                }

            }
            printIt(beans);
        }

        log.debug("splits: {}", splits);


    }

    private static void printIt(List<StringBuilder> beans) {
        for (StringBuilder bean : beans) {
            for (int i = 0; i < bean.length(); i++) {
                System.out.print(bean.charAt(i));
            }
            System.out.println("");
        }
        System.out.println("");
    }
}

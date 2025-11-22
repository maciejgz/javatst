package pl.mg.javatst.codewars;

import java.util.HashSet;
import java.util.Set;

public class SudokuValidator {

    public static void main(String[] args) {

        int[][] sudoku = {
                {5, 3, 4, 6, 7, 8, 9, 1, 2},
                {6, 7, 2, 1, 9, 5, 3, 4, 8},
                {1, 9, 8, 3, 4, 2, 5, 6, 7},
                {8, 5, 9, 7, 6, 1, 4, 2, 3},
                {4, 2, 6, 8, 5, 3, 7, 9, 1},
                {7, 1, 3, 9, 2, 4, 8, 5, 6},
                {9, 6, 1, 5, 3, 7, 2, 8, 4},
                {2, 8, 7, 4, 1, 9, 6, 3, 5},
                {3, 4, 5, 2, 8, 6, 1, 7, 9}
        };
        System.out.println(check(sudoku));
    }

    public static boolean check(int[][] sudoku) {
        int zeros = 0;
        // validate rows
        for (int i = 0; i < 9; i++) {
            Set<Integer> row = new HashSet<>();
            for (int j = 0; j < 9; j++) {
                if (sudoku[i][j] == 0) {
                    zeros++;
                }
                row.add(sudoku[i][j]);
            }
            if (row.size() < 9) {
                return false;
            }
        }
        // validate columns
        for (int i = 0; i < 9; i++) {
            Set<Integer> column = new HashSet<>();
            for (int j = 0; j < 9; j++) {
                column.add(sudoku[j][i]);
                if (sudoku[i][j] == 0) {
                    zeros++;
                }
            }
            if (column.size() < 9) {
                return false;
            }
        }
        // validate squares
        for (int i = 0; i < 9; i = i + 3) {
            for (int j = 0; j < 9; j = j + 3) {
                if (!validateSquare(sudoku, i, j)) {
                    return false;
                }
            }
        }

        if (zeros > 0) {
            return false;
        } else {
            return true;
        }
    }

    private static boolean validateSquare(int[][] sudoku, int i, int j) {
        Set<Integer> column = new HashSet<>();
        for (int k = i; k < i + 3; k++) {
            for (int l = j; l < j + 3; l++) {
                column.add(sudoku[k][l]);
            }

        }
        return column.size() == 9;
    }
}
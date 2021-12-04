package pl.mg.javatst.adventofcode;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class GiantSquid4 {

    public static void main(String[] args) {
        try {
            List<String> input = FileUtil.readLines("adventofcode//4.txt");
            System.out.println("bingo result: " + simulateBingo(input));
            System.out.println("let squid wins: " + letSquidWin(input));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int simulateBingo(List<String> input) {
        List<Integer> marks = new ArrayList<>();
        String[] split = input.get(0).split(",");
        for (String values : split) {
            marks.add(Integer.valueOf(values));
        }

        List<Board> boards = loadBoards(input.subList(2, input.size()));
        for (int i = 0; i < marks.size(); i++) {
            for (Board board : boards) {
                board.markField(marks.get(i));
            }
            for (Board board : boards) {
                if (board.validateBoardWin()) {
                    return board.calcWinResult() * marks.get(i);
                }
            }
        }
        return 0;
    }

    private static int letSquidWin(List<String> input) {
        List<Integer> marks = new ArrayList<>();
        String[] split = input.get(0).split(",");
        for (String values : split) {
            marks.add(Integer.valueOf(values));
        }

        List<Board> boards = loadBoards(input.subList(2, input.size()));
        for (int i = 0; i < marks.size(); i++) {
            for (Board board : boards) {
                board.markField(marks.get(i));
            }
            Iterator<Board> iter = boards.iterator();
            while (iter.hasNext()) {
                Board next = iter.next();
                if (boards.size() > 1 && next.validateBoardWin()) {
                    iter.remove();
                }
            }

            if (boards.size() == 1 && boards.get(0).validateBoardWin()) {
                return boards.get(0).calcWinResult() * marks.get(i);
            }
        }
        return 0;
    }

    private static List<Board> loadBoards(List<String> boardsInput) {
        List<Board> boards = new ArrayList<>();

        for (int i = 0; i < boardsInput.size(); i++) {
            Board board = new Board();
            board.board = new Field[5][5];
            String[] line1 = Arrays.stream(boardsInput.get(i).split(" ")).filter(StringUtils::isNotBlank).toArray(String[]::new);
            for (int j = 0; j < 5; j++) {
                board.board[0][j] = new Field(Integer.valueOf(line1[j]), false);
            }
            i++;

            String[] line2 = Arrays.stream(boardsInput.get(i).split(" ")).filter(StringUtils::isNotBlank).toArray(String[]::new);
            for (int j = 0; j < 5; j++) {
                board.board[1][j] = new Field(Integer.valueOf(line2[j]), false);
            }
            i++;

            String[] line3 = Arrays.stream(boardsInput.get(i).split(" ")).filter(StringUtils::isNotBlank).toArray(String[]::new);
            for (int j = 0; j < 5; j++) {
                board.board[2][j] = new Field(Integer.valueOf(line3[j]), false);
            }
            i++;

            String[] line4 = Arrays.stream(boardsInput.get(i).split(" ")).filter(StringUtils::isNotBlank).toArray(String[]::new);
            ;
            for (int j = 0; j < 5; j++) {
                board.board[3][j] = new Field(Integer.valueOf(line4[j]), false);
            }
            i++;

            String[] line5 = Arrays.stream(boardsInput.get(i).split(" ")).filter(StringUtils::isNotBlank).toArray(String[]::new);
            for (int j = 0; j < 5; j++) {
                board.board[4][j] = new Field(Integer.valueOf(line5[j]), false);
            }
            boards.add(board);
            i = i + 1;
        }
        System.out.println(boards.size());
        return boards;
    }

    private static class Board {

        public Field[][] board;

        public boolean validateBoardWin() {
            boolean result = false;

            //rows
            for (Field[] fields : board) {
                boolean rowMatch = true;
                for (Field field : fields) {
                    if (!field.marked) {
                        rowMatch = false;
                        break;
                    }
                }
                if (rowMatch) {
                    return true;
                }
            }

            //columns
            for (int i = 0; i < board.length; i++) {
                boolean columnMatch = true;
                for (int j = 0; j < board.length; j++) {
                    if (!board[j][i].marked) {
                        columnMatch = false;
                        break;
                    }
                }
                if (columnMatch) {
                    return true;
                }
            }
            return false;
        }

        public void markField(int value) {
            for (Field[] fields : board) {
                for (Field field : fields) {
                    if (field.value == value) {
                        field.marked = true;
                    }
                }
            }
        }

        public int calcWinResult() {
            int sum = 0;
            for (Field[] fields : board) {
                for (Field field : fields) {
                    if (!field.marked) {
                        sum += field.value;
                    }
                }
            }
            return sum;
        }
    }

    private static class Field {
        public int value;
        public boolean marked = false;

        public Field(int value, boolean marked) {
            this.value = value;
            this.marked = marked;
        }
    }
}

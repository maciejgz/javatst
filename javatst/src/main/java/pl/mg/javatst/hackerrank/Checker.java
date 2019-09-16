package pl.mg.javatst.hackerrank;

import java.util.Comparator;

/**
 * https://www.hackerrank.com/challenges/ctci-comparator-sorting
 * 35 points (max)
 */
public class Checker implements Comparator<Player> {

    @Override
    public int compare(Player a, Player b) {
        if (a == null || b == null) {
            return 0;
        }

        if(a.score > b.score) {
            return -1;
        } else if (a.score == b.score) {
            return a.name.compareTo(b.name);
        } else return 1;
    }
}

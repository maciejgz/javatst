package pl.mg.javatsts.leetcode;

/**
 * https://adventofcode.com/2020/day/12
 * <p>
 * Your ferry made decent progress toward the island, but the storm came in faster than anyone expected. The ferry needs to take evasive actions!
 * <p>
 * Unfortunately, the ship's navigation computer seems to be malfunctioning; rather than giving a route directly to safety, it produced extremely circuitous instructions. When the captain uses the PA system to ask if anyone can help, you quickly volunteer.
 * <p>
 * The navigation instructions (your puzzle input) consists of a sequence of single-character actions paired with integer input values. After staring at them for a few minutes, you work out what they probably mean:
 * <p>
 * Action N means to move north by the given value.
 * Action S means to move south by the given value.
 * Action E means to move east by the given value.
 * Action W means to move west by the given value.
 * Action L means to turn left the given number of degrees.
 * Action R means to turn right the given number of degrees.
 * Action F means to move forward by the given value in the direction the ship is currently facing.
 * The ship starts by facing east. Only the L and R actions change the direction the ship is facing. (That is, if the ship is facing east and the next instruction is N10, the ship would move north 10 units, but would still move east if the following action were F.)
 * <p>
 * For example:
 * <p>
 * F10
 * N3
 * F7
 * R90
 * F11
 * These instructions would be handled as follows:
 * <p>
 * F10 would move the ship 10 units east (because the ship starts by facing east) to east 10, north 0.
 * N3 would move the ship 3 units north to east 10, north 3.
 * F7 would move the ship another 7 units east (because the ship is still facing east) to east 17, north 3.
 * R90 would cause the ship to turn right by 90 degrees and face south; it remains at east 17, north 3.
 * F11 would move the ship 11 units south to east 17, south 8.
 * At the end of these instructions, the ship's Manhattan distance (sum of the absolute values of its east/west position and its north/south position) from its starting position is 17 + 8 = 25.
 * <p>
 * Figure out where the navigation instructions lead. What is the Manhattan distance between that location and the ship's starting position?
 */
public class RainRisk {

    public static void main(String[] args) {

    }

    private enum Heading {
        N(0),
        E(90),
        S(180),
        W(240);

        public int getDegrees() {
            return degrees;
        }

        private final int degrees;

        Heading(int degrees) {
            this.degrees = degrees;
        }

        public static Heading getByDegrees(int degrees) {
            for (Heading value : Heading.values()) {
                if (value.getDegrees() == degrees) {
                    return value;
                }
            }
            return null;
        }
    }

    private class Position {
        private int x;

        private int y;

        private Heading heading;

        public void move(int x, int y) {
            this.x += x;
            this.y += y;
        }

        public void turn(char turn, int degrees) {
            int dir = heading.getDegrees();
            if (turn == 'L') {
                int newDir = dir - degrees;
                if (newDir < 0) {
                    newDir = 360 + newDir;
                }
                this.heading = Heading.getByDegrees(newDir);
            } else {
                int newDir = dir + degrees;
                if (newDir > 360) {
                    newDir = newDir - 360;
                }
                this.heading = Heading.getByDegrees(newDir);

            }
        }
    }
}

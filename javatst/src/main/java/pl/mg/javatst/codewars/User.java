package pl.mg.javatst.codewars;

public class User {

    public static void main(String[] args) {
        User user = new User();
        System.out.println(user.rank); // => -8
        System.out.println(user.progress); // => 0
        user.incProgress(8);
        System.out.println(user.progress); // => 0 // progress is now zero
        System.out.println(user.rank); // => -7
    }

    public int rank = -8;
    public int progress = 0;


    void incProgress(int rank) throws IllegalArgumentException {
        if (rank < -8 || rank > 8 || rank == 0) {
            throw new IllegalArgumentException();
        }
        if (this.rank == rank) {
            progress = progress + 3;
        } else if (this.rank == rank + 1 || (this.rank == 1 && rank == -1)) {
            progress = progress + 1;
        } else if (rank > this.rank) {
            progress = progress + (10 * calcDifference(this.rank, rank) * calcDifference(this.rank, rank));
        }
        if (progress >= 100) {
            incRank((int) Math.floor(progress / 100));
            progress = progress % 100;
        }
    }

    int calcDifference(int userRank, int progressRank) {
        if (userRank < 0 && progressRank > 0) {
            return Math.abs(progressRank + Math.abs(userRank)) - 1;
        } else {
            return Math.abs(Math.abs(progressRank) - Math.abs(userRank));
        }
    }

    void incRank(int incr) {
        if (this.rank < 0 && (this.rank + incr > -1)) {
            this.rank = this.rank + incr + 1;
        } else if (this.rank != 8) {
            this.rank = this.rank + incr;
        } else {
            this.progress = 0;
        }
    }
}

package pl.mg.javatst.hackerrank;

/**
 * Emma is playing a new mobile game that starts with consecutively numbered clouds. Some of the clouds are thunderheads
 * and others are cumulus. She can jump on any cumulus cloud having a number that is equal to the number of the current
 * cloud plus  or . She must avoid the thunderheads. Determine the minimum number of jumps it will take Emma to jump from
 * her starting postion to the last cloud. It is always possible to win the game.
 * <p>
 * For each game, Emma will get an array of clouds numbered  if they are safe or  if they must be avoided. For example,
 * indexed from . The number on each cloud is its index in the list so she must avoid the clouds at indexes  and . She
 * could follow the following two paths:  or . The first path takes jumps while the second takes .
 * <p>
 * Function Description
 * <p>
 * Complete the jumpingOnClouds function in the editor below. It should return the minimum number of jumps required,
 * as an integer.
 * <p>
 * jumpingOnClouds has the following parameter(s):
 * <p>
 * c: an array of binary integers
 * Input Format
 * <p>
 * The first line contains an integer , the total number of clouds. The second line contains  space-separated binary
 * integers describing clouds  where .
 * <p>
 * Constraints
 * <p>
 * Output Format
 * <p>
 * Print the minimum number of jumps needed to win the game.
 * <p>
 * Sample Input 0
 * <p>
 * 7
 * 0 0 1 0 0 1 0
 * Sample Output 0
 * <p>
 * 4
 * Explanation 0:
 * Emma must avoid  and . She can win the game with a minimum of  jumps:
 *
 * 100%
 *
 */
public class JumpingOnClouds {


    static int jumpingOnClouds(int[] c) {
        int result = 0;
        for (int i = 0; i < c.length; i++) {
            if (i == c.length - 1) {
                continue;
            }
            if ((i + 2) < c.length && c[i + 2] != 1) {
                result++;
                i++;
            } else {
                result++;
            }
        }
        return result;
    }

}

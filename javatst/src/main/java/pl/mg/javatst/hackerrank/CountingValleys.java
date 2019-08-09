package pl.mg.javatst.hackerrank;

/**
 * Gary is an avid hiker. He tracks his hikes meticulously, paying close attention to small details like topography. During his last hike he took exactly  steps. For every step he took, he noted if it was an uphill, , or a downhill,  step. Gary's hikes start and end at sea level and each step up or down represents a  unit change in altitude. We define the following terms:
 * <p>
 * A mountain is a sequence of consecutive steps above sea level, starting with a step up from sea level and ending with a step down to sea level.
 * A valley is a sequence of consecutive steps below sea level, starting with a step down from sea level and ending with a step up to sea level.
 * Given Gary's sequence of up and down steps during his last hike, find and print the number of valleys he walked through.
 * <p>
 * For example, if Gary's path is , he first enters a valley  units deep. Then he climbs out an up onto a mountain  units high. Finally, he returns to sea level and ends his hike.
 * <p>
 * Function Description
 * <p>
 * Complete the countingValleys function in the editor below. It must return an integer that denotes the number of valleys Gary traversed.
 * <p>
 * countingValleys has the following parameter(s):
 * <p>
 * n: the number of steps Gary takes
 * s: a string describing his path
 * Input Format
 * <p>
 * The first line contains an integer , the number of steps in Gary's hike.
 * The second line contains a single string , of  characters that describe his path.
 * <p>
 * Constraints
 * <p>
 * Output Format
 * <p>
 * Print a single integer that denotes the number of valleys Gary walked through during his hike.
 * <p>
 * Sample Input
 * <p>
 * 8
 * UDDDUDUU
 * Sample Output
 * <p>
 * 1
 * Explanation
 * <p>
 * If we represent _ as sea level, a step up as /, and a step down as \, Gary's hike can be drawn as:
 * <p>
 * _/\      _
 * \    /
 * \/\/
 * He enters and leaves one valley.
 *
 * 100%
 */
public class CountingValleys {


    static int countingValleys(int n, String s) {
        int result = 0;
        int level = 0;
        for (char c : s.toCharArray()) {
            if (c == 'D') {
                if (level == 0) {
                    result++;
                }
                level--;
            } else if (c == 'U') {
                level++;
            }
        }
        return result;
    }

}

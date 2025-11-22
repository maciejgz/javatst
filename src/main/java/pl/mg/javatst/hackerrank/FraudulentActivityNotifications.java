package pl.mg.javatst.hackerrank;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://www.hackerrank.com/challenges/fraudulent-activity-notifications
 * 40 points - max
 */
public class FraudulentActivityNotifications {

    public static void main(String[] args) {
        int[] arr = {10, 20, 30, 40, 50};
        activityNotifications(arr, 3);
    }

    private static float getMedian(int[] freq, int d) {
        if (d % 2 == 1) {
            int center = 0;
            for (int i = 0; i < freq.length; i++) {
                center = center + freq[i];
                if (center > d / 2) {
                    return i;
                }
            }
        } else {
            int count = 0;
            int first = -1;
            int second = -1;
            for (int i = 0; i < freq.length; i++) {
                count = count + freq[i];

                if (count == d / 2) {
                    first = i;
                } else if (count > d / 2) {
                    if (first < 0) {
                        first = i;
                    }
                    second = i;
                    return ((float) first + (float) second) / 2;
                }
            }
        }
        return 0f;
    }

    static int activityNotifications(int[] expenditure, int d) {
        int count = 0;
        int freq[] = new int[201];
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < expenditure.length; i++) {
            while (i < d) {
                q.add(expenditure[i]);
                freq[expenditure[i]] = freq[expenditure[i]] + 1;
                i++;
            }

            float median = getMedian(freq, d);

            if (expenditure[i] >= 2 * median) {
                count++;
            }
            int elem = q.remove();
            freq[elem] = freq[elem] - 1;

            q.add(expenditure[i]);
            freq[expenditure[i]] = freq[expenditure[i]] + 1;
        }

        return count;
    }

}

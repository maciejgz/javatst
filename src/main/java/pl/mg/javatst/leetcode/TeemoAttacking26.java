package pl.mg.javatst.leetcode;


import java.util.Arrays;

/**
 * https://leetcode.com/explore/challenge/card/september-leetcoding-challenge/557/week-4-september-22nd-september-28th/3473/
 * In LOL world, there is a hero called Teemo and his attacking can make his enemy Ashe be in poisoned condition.
 * Now, given the Teemo's attacking ascending time series towards
 * Ashe and the poisoning time duration per Teemo's attacking, you need to output the total time that Ashe is in poisoned condition.
 */
public class TeemoAttacking26 {

    public int findPoisonedDuration(int[] timeSeries, int duration) {
        int poisonTime = 0;
        Arrays.sort(timeSeries);
        int poisonedTo = 0;
        for (int timePoint : timeSeries) {
            int poisonedToLocally = timePoint + duration;
            if (poisonedToLocally >= poisonedTo && poisonedTo > timePoint) {
                poisonTime += poisonedToLocally - poisonedTo;
                poisonedTo = poisonedToLocally;
            } else if(poisonedToLocally >= poisonedTo) {
                poisonTime += poisonedToLocally - timePoint;
                poisonedTo = poisonedToLocally;
            }
        }
        return poisonTime;
    }

}

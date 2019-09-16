package pl.mg.javatst.hackerrank;

import org.junit.Assert;
import org.junit.Test;

public class FraudulentActivityNotificationsTest {

    @Test
    public void activityNotifications() {
        int[] arr = {10, 20, 30, 40, 50};
        Assert.assertEquals(1,
                FraudulentActivityNotifications.activityNotifications(arr, 3));

        int[] arr2 = {1, 2, 3, 4, 4};
        Assert.assertEquals(0,
                FraudulentActivityNotifications.activityNotifications(arr2, 4));

    }
}
package pl.mg.javatst.cert.ocp;

import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

@Slf4j
public class DateAndTime {

    public static void main(String[] args) {

        String m1 = Duration.of(1, ChronoUnit.MINUTES).toString();
        String m2 = Duration.ofMinutes(1).toString();
        String s = Duration.of(60, ChronoUnit.SECONDS).toString();

        System.out.println(m1);
        System.out.println(s);

    }
}

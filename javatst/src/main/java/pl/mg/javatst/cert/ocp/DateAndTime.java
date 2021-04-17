package pl.mg.javatst.cert.ocp;

import lombok.extern.slf4j.Slf4j;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAmount;

@Slf4j
public class DateAndTime {

    public static void main(String[] args) {

        String m1 = Duration.of(1, ChronoUnit.MINUTES).toString();
        String m2 = Duration.ofMinutes(1).toString();
        String s = Duration.of(60, ChronoUnit.SECONDS).toString();

        System.out.println(m1);
        System.out.println(s);


        Period daysPeriod = Period.ofDays(20);
        System.out.println(daysPeriod);


        ZoneId zoneId = ZoneId.of("Europe/Warsaw");


        //local date time
        LocalDateTime d1 = LocalDateTime.of(2021, Month.APRIL, 17, 11, 24);
        LocalDateTime d2 = LocalDateTime.of(2021, Month.APRIL, 18, 11, 24);
        //date time zoned
        ZonedDateTime zoned1 = ZonedDateTime.of(d1, ZoneId.of("UTC"));
        System.out.println(d1.format(DateTimeFormatter.ISO_DATE_TIME));
        System.out.println(zoned1.format(DateTimeFormatter.ISO_DATE_TIME));
        //instant
        Instant instant = Instant.now();
        System.out.println("instant: " + instant);
        //plus methods
        d2 = d1.plus(Period.ofDays(1));
        System.out.println("D2: " + d2.format(DateTimeFormatter.ISO_DATE_TIME));

        //periods
        Period period = Period.ofDays(2);
        System.out.println("period: " + period);

        //durations
        Duration duration = Duration.ofDays(14L);
        System.out.println("duration: " + duration);


    }
}

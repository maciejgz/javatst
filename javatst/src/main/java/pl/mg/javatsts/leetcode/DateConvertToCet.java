package pl.mg.javatsts.leetcode;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class DateConvertToCet {

    public static void main(String[] args) {
        ZonedDateTime cetDateTime = ZonedDateTime.now(ZoneId.of("CET"));
        ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("UTC"));
        System.out.println("UTC: " + zonedDateTime);
        System.out.println("CET: " + cetDateTime);

        Instant nowUtc = Instant.from(zonedDateTime);
        System.out.println("instantUTC: " + nowUtc);
        Instant nowCET = Instant.from(cetDateTime);
        System.out.println("instantCET: " + nowCET);


        System.out.println(LocalDateTime.ofInstant(nowCET, ZoneId.of("UTC")));

    }
}

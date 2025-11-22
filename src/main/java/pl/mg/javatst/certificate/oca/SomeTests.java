package pl.mg.javatst.certificate.oca;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class SomeTests {

  public static void main(String[] args) {
    short a = 10;
    short b = 3;

    System.out.println(a / b);

    short c = 14;
    float d = 13;
    double e = 30;

    System.out.println(c * d / e);
    String sample = "";

    String result = "AniMaL ".trim().toLowerCase().replace('a', 'A');
    System.out.println(result);

    LocalDateTime now = LocalDateTime.now();
    ZonedDateTime zonedDateTime = ZonedDateTime.now().withZoneSameInstant(ZoneId.of("UTC"));

    System.out.println(now);
    System.out.println(zonedDateTime);
  }
}

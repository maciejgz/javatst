package pl.mg.javatst.certificate.oca;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class DatesTests {

    public static void main(String[] args) {

        System.out.println(LocalDate.now());
        System.out.println(LocalTime.now());
        System.out.println(LocalDateTime.now());

        //tworzenie daty 2018-06-21
        LocalDate standard = LocalDate.of(2018, 6, 21);
    }
}

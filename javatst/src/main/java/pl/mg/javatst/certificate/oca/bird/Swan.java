package pl.mg.javatst.certificate.oca.bird;

import pl.mg.javatst.certificate.oca.Bird;

public class Swan extends Bird {

    /**
     * Dostęp protected do elemntów klasy wyżej
     */
    public void swim() {
        floatInWater();
        System.out.println(text);
    }

    /**
     * Odniesienie do elementów tego obiektu
     */
    public void helpOtherSwanSwim() {
        Swan other = new Swan();
        other.floatInWater();
        System.out.println(other.text);
    }


    public void helpOtherBirdSwim() {
        Bird other = new Bird();
        //other.floatInWater(); --to nie zadziała, bo jesteśmy w innym pakiecie i atakujemy
    }
}

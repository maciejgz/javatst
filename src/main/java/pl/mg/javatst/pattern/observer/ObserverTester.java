package pl.mg.javatst.pattern.observer;

import java.util.Observable;
import java.util.Observer;


/**
 * Observer pattern simple implementation.
 */
public class ObserverTester {


    public static void main(String[] args) {
        Observable subject = new Subject();

        ((Subject) subject).setState(0);


        Observer binary = new BinaryObserver(subject);
        Observer octa = new OctalObserver(subject);

        ((Subject) subject).setState(12);


        ((Subject) subject).setState(1000);

    }

}

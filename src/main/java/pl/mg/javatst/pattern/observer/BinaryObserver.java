package pl.mg.javatst.pattern.observer;

import java.util.Observable;
import java.util.Observer;

public class BinaryObserver implements Observer {

    private Observable observable;

    public BinaryObserver(Observable observable) {
        this.observable = observable;
        this.observable.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("Binary string: " + Integer.toBinaryString((Integer) arg));
    }
}

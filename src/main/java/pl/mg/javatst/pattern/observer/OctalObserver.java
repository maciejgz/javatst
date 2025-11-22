package pl.mg.javatst.pattern.observer;

import java.util.Observable;
import java.util.Observer;

public class OctalObserver implements Observer {

    private Observable observable;

    public OctalObserver(Observable observable) {
        this.observable = observable;
        this.observable.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("Octal: " + Integer.toOctalString((Integer) arg));
    }
}

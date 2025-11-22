package pl.mg.javatst.pattern.observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class Subject extends Observable {


    private List<Observer> observers = new ArrayList<>();
    private int state;


    public void setState(int newValue) {
        System.out.println("Subject changed to: " + newValue);
        this.state = newValue;
        this.notifyObservers();
    }

    public int getState() {
        return this.state;
    }

    @Override
    public synchronized void addObserver(java.util.Observer o) {
        this.observers.add(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : this.observers) {
            observer.update(this, state);
        }
    }

    @Override
    public synchronized int countObservers() {
        return this.observers.size();
    }
}

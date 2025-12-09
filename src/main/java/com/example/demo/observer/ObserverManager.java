package com.example.demo.observer;

import java.util.ArrayList;
import java.util.List;

public class ObserverManager implements Subject {

    private static ObserverManager instance;
    private final List<Observer> observers = new ArrayList<>();

    private ObserverManager() {}

    public static ObserverManager getInstance() {
        if (instance == null) {
            instance = new ObserverManager();
        }
        return instance;
    }

    @Override
    public void addObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers(String mensaje) {
        for (Observer obs : observers) {
            obs.update(mensaje);
        }
    }

    public int getCantidad() {
        return observers.size();
    }
}

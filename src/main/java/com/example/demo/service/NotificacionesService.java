package com.example.demo.service;

import com.example.demo.model.Usuario;
import com.example.demo.observer.Subject;
import com.example.demo.observer.Observer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotificacionesService implements Subject {

    private final List<Observer> observadores = new ArrayList<>();

    @Override
    public void addObserver(Observer observer) {
        observadores.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observadores.remove(observer);
    }

    @Override
    public void notifyObservers(String mensaje) {
        observadores.forEach(o -> o.update(mensaje));
    }

    public int contarObservadores() {
        return observadores.size();
    }
}

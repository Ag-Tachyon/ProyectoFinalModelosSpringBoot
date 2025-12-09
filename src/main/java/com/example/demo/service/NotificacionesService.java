package com.example.demo.service;

import com.example.demo.model.Usuario;
import com.example.demo.observer.ISubject;
import com.example.demo.observer.IObserver;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotificacionesService implements ISubject {

    private final List<IObserver> observadores = new ArrayList<>();

    @Override
    public void agregarObservador(IObserver observer) {
        observadores.add(observer);
    }

    @Override
    public void eliminarObservador(IObserver observer) {
        observadores.remove(observer);
    }

    @Override
    public void notificar(String mensaje) {
        observadores.forEach(o -> o.actualizar(mensaje));
    }

    public int contarObservadores() {
        return observadores.size();
    }
}

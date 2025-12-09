package com.example.demo.observer;

import java.util.ArrayList;
import java.util.List;

public class UsuarioObserver implements Observer {

    private final String nombre;
    private final List<String> bandeja = new ArrayList<>();

    public UsuarioObserver(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void update(String mensaje) {
        bandeja.add(mensaje);
        System.out.println("Notificación enviada a " + nombre + ": " + mensaje);
    }

    public List<String> getBandeja() {
        return bandeja;
    }

    public String getNombre() {
        return nombre;
    }
}

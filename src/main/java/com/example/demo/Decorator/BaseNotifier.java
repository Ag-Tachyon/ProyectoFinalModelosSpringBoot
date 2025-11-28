package com.example.demo.Decorator;

public abstract class BaseNotifier implements Notificacion {

    protected Notificacion wrapper;

    public BaseNotifier(Notificacion wrapper) {
        this.wrapper = wrapper;
    }

    @Override
    public void enviarMensaje(String mensaje) {
        wrapper.enviarMensaje(mensaje);
    }
}

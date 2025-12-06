package com.example.demo.Decorator;

public abstract class BaseNotifier implements Notificacion {

    protected Notificacion wrapper;

    public BaseNotifier(Notificacion wrapper) {
        this.wrapper = wrapper;
    }
    @Override
    public void enviarMensaje(String mensaje, String destinatario) {
        if (wrapper != null) {
            wrapper.enviarMensaje(mensaje, destinatario);
        }else System.out.println("Fin de la cadena de notificaciones");
    }

    public Notificacion getWrapper() {
        return wrapper;
    }
}



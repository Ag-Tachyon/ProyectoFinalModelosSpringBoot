package com.example.demo.Decorator;

public class NotificacionSMS extends BaseNotifier {

    public NotificacionSMS(Notificacion wrapper) {
        super(wrapper);
    }
    @Override
    public void enviarMensaje(String mensaje) {
        System.out.println("Enviando notificacion sms: " + mensaje);
        super.enviarMensaje(mensaje); // es para delegar al decorador anterior
    }
}

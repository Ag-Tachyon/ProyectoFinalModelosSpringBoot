package com.example.demo.Decorator;

public class NotificacionCorreo extends BaseNotifier{

    public NotificacionCorreo(Notificacion wrappe) {
        super(wrappe);
    }
@Override
    public void enviarMensaje(String mensaje) {
        System.out.println("enviando mensaje "+ mensaje);
        super.enviarMensaje(mensaje);
    }
}

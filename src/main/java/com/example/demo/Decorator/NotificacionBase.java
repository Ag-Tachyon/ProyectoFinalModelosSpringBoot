package com.example.demo.Decorator;

public class NotificacionBase implements Notificacion {
@Override
    public void enviarMensaje(String mensaje){
    System.out.printf("mensaje de prueba "+ mensaje);
}
}

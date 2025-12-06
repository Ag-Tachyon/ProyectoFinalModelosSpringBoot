package com.example.demo.Decorator;

import org.springframework.stereotype.Component;


@Component
public class NotificacionBase implements Notificacion {
@Override
    public void enviarMensaje(String mensaje,String destinatario) {
    System.out.printf("mensaje de prueba "+ mensaje+ "Hola "+ destinatario);
}
}

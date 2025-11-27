package com.example.demo.Decorator;

import com.example.demo.model.Usuario;

public class MensajePromocion {

    private Usuario cliente;
    private Notificacion notificador;

    public MensajePromocion(Notificacion notificador, Usuario cliente) {
        this.notificador = notificador;
        this.cliente = cliente;
    }

    public void enviarMensaje(String mensaje) {
        System.out.println("Mensaje de Promoción para " + cliente);
        notificador.enviarMensaje(mensaje);
    }
}

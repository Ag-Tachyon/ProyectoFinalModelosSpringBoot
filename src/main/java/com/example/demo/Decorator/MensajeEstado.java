package com.example.demo.Decorator;

import com.example.demo.model.Usuario;

public class MensajeEstado {

    private Usuario cliente;
    private Notificacion notificador;

    public MensajeEstado(Notificacion notificador, Usuario cliente) {
        this.notificador = notificador;
        this.cliente = cliente;
    }

    public void enviarMensaje(String mensaje) {
        System.out.println("Mensaje de Estado para " + cliente);
        notificador.enviarMensaje(mensaje);
    }
}

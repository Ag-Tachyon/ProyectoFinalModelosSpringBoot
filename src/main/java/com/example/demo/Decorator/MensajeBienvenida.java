package com.example.demo.Decorator;

import com.example.demo.model.Usuario;

public class MensajeBienvenida extends BaseNotifier {
    private final Usuario cliente;

    public MensajeBienvenida(Notificacion wrapper, Usuario cliente) {
        super(wrapper);
        this.cliente = cliente;
    }

    @Override
    public void enviarMensaje(String mensaje, String destinatario) {
        System.out.println("👋 Bienvenida enviada a: " + cliente.getNombreUsuario());
        String mensajeDecorado =
                "¡Bienvenido " + cliente.getNombreUsuario() + "! 🎉\n" + mensaje;

        super.enviarMensaje(mensajeDecorado, destinatario);
    }
}


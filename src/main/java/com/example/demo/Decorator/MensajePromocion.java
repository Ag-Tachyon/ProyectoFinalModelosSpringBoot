package com.example.demo.Decorator;

import com.example.demo.model.Usuario;

public class MensajePromocion extends BaseNotifier {

    private final Usuario cliente;

    public MensajePromocion(Notificacion wrapper, Usuario cliente) {
        super(wrapper);
        this.cliente = cliente;
    }

    @Override
    public void enviarMensaje(String mensaje, String destinatario) {
        System.out.println("🎉 Mensaje de PROMOCIÓN para: " + cliente.getNombreUsuario());
        String mensajeDecorado = "✨ Oferta especial para ti ✨\n" + mensaje;

        super.enviarMensaje(mensajeDecorado, destinatario);
    }
}


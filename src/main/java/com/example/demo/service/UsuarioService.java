package com.example.demo.service;

import com.example.demo.Decorator.*;
import com.example.demo.model.Usuario;
import com.example.demo.singleton.UsuarioData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {

    private final List<Usuario> usuarios = new ArrayList<>();
    private final Notificacion notificacionCorreo;
    private UsuarioData usuarioData;
    @Autowired
    public UsuarioService(NotificacionCorreo notificacionCorreo) {
        this.notificacionCorreo = notificacionCorreo;
    }

    public void guardarUsuario(Usuario u) {
        usuarios.add(u);    Notificacion canal = notificacionCorreo;
        usuarioData.guardarDatos(usuarios);
        
        Notificacion notificador =
                new MensajeBienvenida(
                        new MensajePromocion(
                                canal,u),
                        u
                );

        notificador.enviarMensaje(
                "Este es un mensaje con todos los decoradores!",
                u.getCorreoUsuario()
        );

    }

    public Usuario buscarPorNombre(String nombre) {
        return usuarios.stream()
                .filter(x -> x.getNombreUsuario().equalsIgnoreCase(nombre))
                .findFirst()
                .orElse(null);
    }
}

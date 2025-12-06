package com.example.demo.service;

import com.example.demo.Decorator.*;
import com.example.demo.model.Usuario;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {

    private final List<Usuario> usuarios = new ArrayList<>();
    private final NotificacionCorreo notificacionCorreo;
    @Autowired
    public UsuarioService(NotificacionCorreo notificacionCorreo) {
        this.notificacionCorreo = notificacionCorreo;
    }

    public void guardarUsuario(Usuario u) {
        usuarios.add(u);
        //solo proceso de prueba para la notificacion apenas crea el mensaje
        notificacionCorreo.enviarMensaje("hola muchacco","juancanon25@gmail.com");
    }

    public Usuario buscarPorNombre(String nombre) {
        return usuarios.stream()
                .filter(x -> x.getNombreUsuario().equalsIgnoreCase(nombre))
                .findFirst()
                .orElse(null);
    }
}

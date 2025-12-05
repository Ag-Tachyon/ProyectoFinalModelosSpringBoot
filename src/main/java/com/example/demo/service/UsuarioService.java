package com.example.demo.service;

import com.example.demo.model.Usuario;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {

    private final List<Usuario> usuarios = new ArrayList<>();

    public void guardarUsuario(Usuario u) {
        usuarios.add(u);
    }

    public Usuario buscarPorNombre(String nombre) {
        return usuarios.stream()
                .filter(x -> x.getNombreUsuario().equalsIgnoreCase(nombre))
                .findFirst()
                .orElse(null);
    }
}

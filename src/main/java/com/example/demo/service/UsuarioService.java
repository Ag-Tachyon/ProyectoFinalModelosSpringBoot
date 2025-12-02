package com.example.demo.service;

import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioFileRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioFileRepository repo;

    public UsuarioService(UsuarioFileRepository repo) {
        this.repo = repo;
    }

    public void registrar(Usuario usuario) {
        // Validación básica
        if (repo.findByNombre(usuario.getNombreUsuario()) != null) {
            throw new IllegalArgumentException("El nombre de usuario ya existe");
        }

        repo.save(usuario);
    }

    public boolean existe(String nombreUsuario) {
        return repo.findByNombre(nombreUsuario) != null;
    }
}

package com.example.demo.repository;

import com.example.demo.model.Usuario;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UsuarioFileRepository {

    private final String FILE_PATH = "data/usuarios.data";
    private List<Usuario> usuarios = new ArrayList<>();

    public UsuarioFileRepository() {
        cargarArchivo();
    }

    @SuppressWarnings("unchecked")
    private void cargarArchivo() {
        File file = new File(FILE_PATH);

        if (!file.exists()) {
            guardarArchivo(); // crear archivo vacío
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            usuarios = (List<Usuario>) ois.readObject();
        } catch (Exception e) {
            usuarios = new ArrayList<>();
        }
    }

    private void guardarArchivo() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(usuarios);
        } catch (IOException e) {
            throw new RuntimeException("Error guardando usuarios.data", e);
        }
    }

    public synchronized void save(Usuario usuario) {
        usuarios.add(usuario);
        guardarArchivo();
    }

    public List<Usuario> findAll() {
        return usuarios;
    }

    public Usuario findByNombre(String nombre) {
        return usuarios.stream()
                .filter(u -> u.getNombreUsuario().equalsIgnoreCase(nombre))
                .findFirst()
                .orElse(null);
    }
}

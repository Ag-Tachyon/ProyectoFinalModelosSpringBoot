package com.example.demo.repository;

import com.example.demo.model.Usuario;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

@Repository
public class UsuarioFileRepository {

    private final String FILE_PATH = "usuarios.data";

    // Usamos una lista sincronizada o synchronized methods para thread safety
    private List<Usuario> usuarios = Collections.synchronizedList(new ArrayList<>());

    public UsuarioFileRepository() {
        cargarArchivo();
    }

    // --- Métodos de Persistencia Interna ---

    @SuppressWarnings("unchecked")
    private void cargarArchivo() {
        File file = new File(FILE_PATH);

        // ... (El resto de cargarArchivo es el mismo) ...
        if (!file.exists()) {
            guardarArchivo();
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            List<Usuario> loadedUsers = (List<Usuario>) ois.readObject();
            usuarios = Collections.synchronizedList(new ArrayList<>(loadedUsers));
        } catch (Exception e) {
            usuarios = Collections.synchronizedList(new ArrayList<>());
        }
    }

    private void guardarArchivo() {
        try {
            File carpeta = new File("data");
            if (!carpeta.exists()) {
                carpeta.mkdirs();
            }

            // Se escribe la lista actual a disco
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
                oos.writeObject(usuarios);
            }

        } catch (IOException e) {
            throw new RuntimeException("Error guardando usuarios.data", e);
        }
    }

    // --- NUEVO MÉTODO para guardar cambios hechos a objetos referenciados ---
    /**
     * Persiste la lista actual en memoria al archivo.
     * Se usa cuando un objeto Usuario encontrado y modificado es guardado.
     */
    public synchronized void guardarCambios() {
        guardarArchivo();
    }

    // --- Métodos de Acceso a Datos (DAO) ---

    /**
     * Guarda o actualiza un usuario. Si el usuario existe (por nombre), lo reemplaza.
     * Si no existe, lo agrega.
     * @param usuario El usuario a guardar o actualizar.
     */
    public synchronized void save(Usuario usuario) {
        Usuario existingUser = findByNombre(usuario.getNombreUsuario());

        if (existingUser != null) {
            // Lógica de actualización: encontramos la posición del usuario existente y lo reemplazamos
            int index = -1;
            for (int i = 0; i < usuarios.size(); i++) {
                if (usuarios.get(i).getNombreUsuario().equalsIgnoreCase(usuario.getNombreUsuario())) {
                    index = i;
                    break;
                }
            }
            if (index != -1) {
                usuarios.set(index, usuario); // Reemplazar con el objeto actualizado
            }
        } else {
            // Lógica de creación
            usuarios.add(usuario);
        }

        guardarArchivo(); // Persiste el cambio a disco.
    }

    public List<Usuario> findAll() {
        // Devolver una copia para evitar modificaciones externas no sincronizadas.
        return new ArrayList<>(usuarios);
    }

    public Usuario findByNombre(String nombre) {
        // Devuelve el objeto Usuario que está en la lista interna.
        // Si este objeto se modifica, el repositorio verá el cambio (por referencia).
        return usuarios.stream()
                .filter(u -> u.getNombreUsuario().equalsIgnoreCase(nombre))
                .findFirst()
                .orElse(null);
    }
}
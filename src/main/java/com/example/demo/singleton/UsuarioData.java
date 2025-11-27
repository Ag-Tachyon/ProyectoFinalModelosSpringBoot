package com.example.demo.singleton;
import com.example.demo.model.Mascota;

import com.example.demo.model.Usuario;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

// Implementa DataManager y el patrón Singleton
public class UsuarioData implements DataManager<Usuario> {

    // 1. Instancia Estática Privada (Singleton)
    private static UsuarioData administrador;

    // Ruta del archivo de persistencia
    private static final String RUTA_USUARIOS = "usuarios_data.bin";

    // 2. Constructor Privado
    private UsuarioData() {
        System.out.println("UsuarioData: Instancia Singleton creada.");
    }

    // 3. Método Estático de Acceso Global
    public static UsuarioData getAdministrador() {
        if (administrador == null) {
            administrador = new UsuarioData();
        }
        return administrador;
    }

    // --- Implementación de DataManager con Persistencia Real ---

    /**
     * Lee y retorna la lista de usuarios desde el archivo binario.
     * Utiliza ObjectInputStream para deserializar la lista.
     */
    @Override
    public List<Usuario> leerDatos() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(RUTA_USUARIOS))) {
            System.out.println("✅ UsuarioData: Leyendo lista de usuarios desde archivo...");
            // Retorna la lista de Usuarios guardada
            return (List<Usuario>) ois.readObject();
        } catch (FileNotFoundException e) {
            // Es la primera vez que se ejecuta. Retorna una lista inicial de usuarios.
            System.out.println("⚠️ Archivo de usuarios no encontrado. Creando lista inicial de Admin.");
            return crearUsuariosIniciales();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("🔴 Error al leer los usuarios: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    /**
     * Guarda la lista de usuarios en el archivo binario.
     * Utiliza ObjectOutputStream para serializar la lista.
     */
    @Override
    public void guardarDatos(List<Usuario> datos) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(RUTA_USUARIOS))) {
            oos.writeObject(datos);
            System.out.println("💾 UsuarioData: " + datos.size() + " usuarios guardados exitosamente.");
        } catch (IOException e) {
            System.err.println("🔴 Error al guardar los usuarios: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Crea un conjunto de usuarios por defecto si el archivo no existe.
     */
    private List<Usuario> crearUsuariosIniciales() {
        List<Usuario> usuarios = new ArrayList<>();
        // Crea un usuario administrador por defecto
        usuarios.add(new Usuario("admin", "admin123", true, new ArrayList<>()));
        // Crea un usuario cliente por defecto
        usuarios.add(new Usuario("cliente", "pass", false, new ArrayList<>()));

        // Guarda esta lista inicial para que el archivo exista la próxima vez
        guardarDatos(usuarios);
        return usuarios;
    }
}
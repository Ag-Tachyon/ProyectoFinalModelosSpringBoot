package com.example.demo.singleton;

import com.example.demo.model.Usuario;
import java.util.ArrayList;
import java.util.List;

// Implementa la interfaz para manejar objetos Usuario
public class UsuarioData implements DataManager<Usuario> {

    // 1. Instancia Estática Privada (El corazón del Singleton)
    private static UsuarioData administrador;

    // Ruta donde se almacenarán los usuarios (simulado)
    private final String rutaUsuarios = "data/usuarios.dat";

    // 2. Constructor Privado
    private UsuarioData() {
        // Inicialización de recursos (ej: conexión a archivos/DB)
    }

    // 3. Método Estático de Acceso Global (getAdministrador() del diagrama)
    public static UsuarioData getAdministrador() {
        if (administrador == null) {
            administrador = new UsuarioData();
        }
        return administrador;
    }

    // --- Implementación de DataManager ---

    @Override
    public List<Usuario> leerDatos() {
        System.out.println("Leyendo usuarios desde: " + rutaUsuarios);
        // Simulación: En un entorno real, aquí iría la lógica de deserialización de archivos o consulta a la DB.

        // Simulación: Retorna una lista dummy para probar
        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(new Usuario("admin", "admin123", true, new ArrayList<>()));
        usuarios.add(new Usuario("cliente", "pass", false, new ArrayList<>()));
        return usuarios;
    }

    @Override
    public void guardarDatos(List<Usuario> datos) {
        System.out.println("Guardando " + datos.size() + " usuarios en: " + rutaUsuarios);
        // Aquí iría la lógica de serialización o escritura en la DB/archivo.
    }
}
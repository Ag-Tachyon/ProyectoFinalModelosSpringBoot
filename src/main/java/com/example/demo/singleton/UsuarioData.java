package com.example.demo.singleton;

import com.example.demo.model.Usuario;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioData implements DataManager<Usuario> {

    private static UsuarioData administrador;

    private static final String RUTA_USUARIOS = "data/usuarios.data";

    private UsuarioData() {
        System.out.println("UsuarioData: Instancia Singleton creada.");
    }

    public static UsuarioData getAdministrador() {
        if (administrador == null) {
            administrador = new UsuarioData();
        }
        return administrador;
    }

    @Override
    public List<Usuario> leerDatos() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(RUTA_USUARIOS))) {
            System.out.println("✅ UsuarioData: Leyendo usuarios...");
            return (List<Usuario>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("⚠️ No existe archivo, creando usuarios iniciales...");
            return crearUsuariosIniciales();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("🔴 Error al leer usuarios: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public void guardarDatos(List<Usuario> datos) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(RUTA_USUARIOS))) {
            oos.writeObject(datos);
            System.out.println("💾 UsuarioData: " + datos.size() + " usuarios guardados.");
        } catch (IOException e) {
            System.err.println("🔴 Error al guardar usuarios: " + e.getMessage());
        }
    }

    private List<Usuario> crearUsuariosIniciales() {
        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(new Usuario("admin", "admin123", true, new ArrayList<>(), "admin@gmail.com"));
        usuarios.add(new Usuario("cliente", "pass", false, new ArrayList<>(), "cliente@gmail.com"));

        guardarDatos(usuarios);
        return usuarios;
    }
}

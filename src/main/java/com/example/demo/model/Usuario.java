package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

public class Usuario {

    // Atributos basados en el diagrama UML
    private String nombreUsuario;
    private String contrasena;
    private boolean esAdmin;
    private List<Mascota> mascotas;

    /**
     * Constructor utilizado para inicializar un objeto Usuario.
     * Este constructor está basado en los parámetros de prueba que usaste,
     * pero tipado correctamente (String, String, boolean, List<Mascota>).
     */
    public Usuario(String nombreUsuario, String contrasena, boolean esAdmin, List<Mascota> mascotas) {
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.esAdmin = esAdmin;

        // Aseguramos que la lista no sea nula
        this.mascotas = (mascotas != null) ? mascotas : new ArrayList<>();
    }

    // --- Getters del diagrama UML y necesarios para el Proxy ---

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getContrasena() {
        // En una aplicación real, no se retornaría la contraseña en texto plano
        return contrasena;
    }

    // Este método es usado por el ProxyServicio para verificar privilegios
    public boolean isEsAdmin() {
        return esAdmin;
    }

    // --- Otros métodos del diagrama UML ---

    // Método para obtener el rol del usuario (simplificado)
    public String getRol() {
        return this.esAdmin ? "Administrador" : "Cliente";
    }

    // Método getMascotas()
    public List<Mascota> getMascotas() {
        return mascotas;
    }

    // Método toString() para representación
    @Override
    public String toString() {
        return "Usuario [nombreUsuario=" + nombreUsuario + ", esAdmin=" + esAdmin +
                ", numMascotas=" + mascotas.size() + "]";
    }
}
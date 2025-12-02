package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nombreUsuario;
    private String contrasena;
    private Boolean esAdmin;
    private List<Mascota> mascotas;

    // 👉 Constructor vacío requerido por Spring
    public Usuario() {}

    // 👉 Constructor completo que tú usas manualmente si quieres
    public Usuario(String nombreUsuario, String contrasena, boolean esAdmin, List<Mascota> mascotas) {
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.esAdmin = esAdmin;
        this.mascotas = (mascotas != null) ? mascotas : new ArrayList<>();
    }

    // --- Getters y Setters necesarios para formularios ---

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Boolean getEsAdmin() {
        return esAdmin;
    }

    public void setEsAdmin(Boolean esAdmin) {
        this.esAdmin = esAdmin;
    }

    public List<Mascota> getMascotas() {
        return mascotas;
    }

    public void setMascotas(List<Mascota> mascotas) {
        this.mascotas = mascotas;
    }

    @Override
    public String toString() {
        return "Usuario [nombreUsuario=" + nombreUsuario + ", esAdmin=" + esAdmin +
                ", numMascotas=" + mascotas.size() + "]";
    }
}

package com.example.demo.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nombreUsuario;
    private String contrasena;
    private Boolean esAdmin;
    private List<Mascota> mascotas;

    // 👉 Constructor completo
    public Usuario(String nombreUsuario, String contrasena, boolean esAdmin, List<Mascota> mascotas) {
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.esAdmin = esAdmin;
        this.mascotas = (mascotas != null) ? mascotas : new ArrayList<>();
    }

    public Usuario() {}

    // Getters y setters
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
}

package com.example.demo.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nombreUsuario;
    private String correoUsuario;
    private String contrasena;
    private Boolean esAdmin;
    private List<Mascota> mascotas;

    // Constructor completo
    public Usuario(String nombreUsuario, String contrasena, boolean esAdmin, List<Mascota> mascotas,String correoUsuario) {
        this.nombreUsuario = nombreUsuario;
        this.correoUsuario = correoUsuario;
        this.contrasena = contrasena;
        this.esAdmin = esAdmin;
        this.mascotas = (mascotas != null) ? mascotas : new ArrayList<>();
    }

    // Constructor vacío → obligatorio para Spring
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

    public String getCorreoUsuario() {
        return correoUsuario;
    }
    public void setCorreoUsuario(String correoUsuario) {
        this.correoUsuario = correoUsuario;
    }
    public Boolean getEsAdmin() {
        return esAdmin;
    }

    // ✔ Getter estilo JavaBean
    public Boolean isEsAdmin() {
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

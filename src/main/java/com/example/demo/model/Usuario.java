package com.example.demo.model;

import com.example.demo.model.Mascota;
import com.example.demo.observer.Observer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Usuario implements Serializable, Observer {

    private static final long serialVersionUID = 1L;

    private String nombreUsuario;
    private String correoUsuario;
    private String contrasena;
    private Boolean esAdmin;
    private List<Mascota> mascotas;

    // 🔔 Lista de notificaciones (para la campanita)
    private List<String> notificaciones = new ArrayList<>();

    // Constructor completo
    public Usuario(String nombreUsuario, String contrasena, boolean esAdmin,
                   List<Mascota> mascotas, String correoUsuario) {

        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.esAdmin = esAdmin;
        this.correoUsuario = correoUsuario;
        this.mascotas = (mascotas != null) ? mascotas : new ArrayList<>();
    }

    // Constructor vacío
    public Usuario() {}

    // ----------------------------------------------------------------
    // 🐕 LÓGICA DE NEGOCIO (Adopción)
    // ----------------------------------------------------------------

    /**
     * Agrega una mascota a la lista de mascotas adoptadas por el usuario.
     */
    public void agregarMascotaAdoptada(Mascota mascota) {
        if (this.mascotas == null) {
            this.mascotas = new ArrayList<>();
        }
        this.mascotas.add(mascota);
    }

    // ----------------------------------------------------------------
    // IMPLEMENTACIÓN DEL OBSERVER
    // ----------------------------------------------------------------
    @Override
    public void update(String mensaje) {
        notificaciones.add(mensaje); // guarda la notificación
        System.out.println("🔔 Notificación para " + nombreUsuario + ": " + mensaje);
    }

    // Para obtener las notificaciones desde AJAX
    public List<String> getNotificaciones() {
        return notificaciones;
    }

    public void limpiarNotificaciones() {
        notificaciones.clear();
    }

    // ----------------------------------------------------------------
    // GETTERS Y SETTERS
    // ----------------------------------------------------------------

    public String getNombreUsuario() {
        return nombreUsuario;
    }
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getCorreoUsuario() {
        return correoUsuario;
    }
    public void setCorreoUsuario(String correoUsuario) {
        this.correoUsuario = correoUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Boolean getEsAdmin() { return esAdmin; }
    public Boolean isEsAdmin() { return esAdmin; }
    public void setEsAdmin(Boolean esAdmin) { this.esAdmin = esAdmin; }


    public List<Mascota> getMascotas() { return mascotas; }
    public void setMascotas(List<Mascota> mascotas) { this.mascotas = mascotas; }
}
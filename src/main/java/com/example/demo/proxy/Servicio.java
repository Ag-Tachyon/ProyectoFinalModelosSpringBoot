package com.example.demo.proxy;

import com.example.demo.model.Usuario; // Necesitas la clase Usuario

public interface Servicio {
    // Definición del método de acceso.
    void acceder(Usuario usuario, String contrasena, boolean esAdmin);
}
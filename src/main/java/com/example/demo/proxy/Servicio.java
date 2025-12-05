package com.example.demo.proxy;

import com.example.demo.model.Usuario;

public interface Servicio {
    boolean acceder(Usuario usuario, String contrasenaIngresada, boolean adminIngresado);
}

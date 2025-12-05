package com.example.demo.proxy;

import com.example.demo.model.Usuario;

public class ServicioReal implements Servicio {

    @Override
    public boolean acceder(Usuario usuario, String contrasena, boolean admin) {
        // Aquí no valida nada, solo devuelve true
        return true;
    }
}

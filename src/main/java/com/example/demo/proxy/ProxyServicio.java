package com.example.demo.proxy;

import com.example.demo.model.Usuario;

public class ProxyServicio implements Servicio {

    private final ServicioReal servicioReal;

    public ProxyServicio() {
        this.servicioReal = new ServicioReal();
    }

    @Override
    public boolean acceder(Usuario usuario, String contrasenaIngresada, boolean adminIngresado) {

        if (usuario == null) {
            System.out.println("❌ Usuario inexistente");
            return false;
        }

        if (!usuario.getContrasena().equals(contrasenaIngresada)) {
            System.out.println("❌ Contraseña incorrecta");
            return false;
        }

        if (usuario.getEsAdmin() != adminIngresado) {
            System.out.println("❌ No tiene permisos para este rol");
            return false;
        }

        System.out.println("✔ Validado por Proxy (rol correcto)");

        return servicioReal.acceder(usuario, contrasenaIngresada, adminIngresado);
    }
}

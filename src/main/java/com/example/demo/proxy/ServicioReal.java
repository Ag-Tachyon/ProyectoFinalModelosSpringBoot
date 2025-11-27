package com.example.demo.proxy;

import com.example.demo.model.Usuario;

public class ServicioReal implements Servicio {

    @Override
    public void acceder(Usuario usuario, String contrasena, boolean esAdmin) {
        // --- Lógica de Negocio Real ---

        System.out.println("ServicioReal: Intentando autenticar al usuario " + usuario.getNombreUsuario() + "...");

        // Simulación de la verificación de credenciales
        if (usuario.getContrasena().equals(contrasena)) {
            String rol = esAdmin ? "ADMINISTRADOR" : "USUARIO ESTÁNDAR";
            System.out.println("🟢 Acceso concedido para " + usuario.getNombreUsuario() + " como " + rol + ".");
            // Aquí iría la lógica para cargar la sesión o redirigir a VentanaMain/VentanaAdmin.
        } else {
            System.err.println("🔴 Fallo en la autenticación. Contraseña incorrecta.");
        }
    }
}

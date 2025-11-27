package com.example.demo.proxy;

import com.example.demo.model.Usuario;

public class ProxyServicio implements Servicio {

    // Referencia al objeto real (agregación del diagrama)
    private ServicioReal servicioReal;

    // Constructor que inicializa el objeto real (Lazy Initialization podría aplicarse aquí)
    public ProxyServicio() {
        this.servicioReal = new ServicioReal();
    }

    // El Proxy implementa el mismo método
    @Override
    public void acceder(Usuario usuario, String contrasena, boolean esAdmin) {

        // --- Lógica de Control del Proxy (Acceso Restringido) ---

        // Ejemplo de lógica adicional: solo permitir el acceso si el usuario es válido
        if (usuario.getNombreUsuario() == null || usuario.getNombreUsuario().trim().isEmpty()) {
            System.err.println("❌ Proxy: Petición bloqueada. Nombre de usuario no proporcionado.");
            // No se llama al ServicioReal
            return;
        }

        if (esAdmin && !usuario.isEsAdmin()) {
            System.err.println("❌ Proxy: Petición bloqueada. Intento de acceso administrativo sin privilegios.");
            // No se llama al ServicioReal
            return;
        }

        // Si todas las comprobaciones del Proxy pasan, delegamos la llamada al objeto real.
        System.out.println("✅ Proxy: Petición validada. Delegando al ServicioReal...");
        servicioReal.acceder(usuario, contrasena, esAdmin);
    }
}

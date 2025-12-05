package com.example.demo.controller;

import com.example.demo.model.Usuario;
import com.example.demo.proxy.ProxyServicio;
import com.example.demo.proxy.Servicio;
import com.example.demo.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    private final UsuarioService usuarioService;
    private final Servicio servicioProxy;

    public LoginController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
        this.servicioProxy = new ProxyServicio(); // el proxy real
    }

    // Mostrar página de login
    @GetMapping("/login")
    public String mostrarLogin() {
        return "login";
    }

    // Procesar formulario de login
    @PostMapping("/login")
    public String procesarLogin(
            @RequestParam String nombre,
            @RequestParam String contrasena
    ) {

        // 1. Buscar al usuario por nombre
        Usuario u = usuarioService.buscarPorNombre(nombre);

        if (u == null) {
            return "redirect:/login?error=notfound"; // usuario no existe
        }

        // 2. El Proxy valida si puede acceder (admin o usuario normal)
        boolean acceso = servicioProxy.acceder(u, contrasena, u.isEsAdmin());

        if (!acceso) {
            return "redirect:/login?error=bad_credentials";
        }

        // 3. Redirección según el rol almacenado en la BD (o lista)
        if (u.isEsAdmin()) {
            return "admin";   // admin.html
        } else {
            return "usuario"; // usuario.html
        }
    }
}

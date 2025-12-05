package com.example.demo.controller;

import com.example.demo.model.Usuario;
import com.example.demo.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/")
    public String mostrarFormulario(Model model) {
        model.addAttribute("usuario", new Usuario()); // 👈 ¡IMPORTANTE!
        return "index";
    }

    @PostMapping("/registrar")
    public String registrarUsuario(Usuario usuario) {
        usuarioService.guardarUsuario(usuario);
        return "inicio_sesion";
    }
}

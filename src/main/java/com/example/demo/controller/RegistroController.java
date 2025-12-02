package com.example.demo.controller;

import com.example.demo.model.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegistroController {

    @GetMapping("/registro")
    public String mostrarFormularioRegistro(Model model) {
        // 👈 Importante: agregar un nuevo Usuario al modelo
        model.addAttribute("usuario", new Usuario());
        return "registro"; // tu archivo registro.html
    }

    @PostMapping("/registro")
    public String procesarRegistro(@ModelAttribute Usuario usuario) {
        // Aquí recibes los datos del formulario
        System.out.println("Usuario registrado: " + usuario.getNombreUsuario());

        // Guardar en archivo .data aquí...

        return "redirect:/";
    }
}

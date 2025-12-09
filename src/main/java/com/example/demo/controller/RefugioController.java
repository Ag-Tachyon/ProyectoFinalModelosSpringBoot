package com.example.demo.controller;

import com.example.demo.absFactory.FabricaRefugio;
import com.example.demo.absFactory.FabricaRefugioGatos;
import com.example.demo.absFactory.FabricaRefugioPerros;
import com.example.demo.dto.RefugioDTO;
import com.example.demo.model.Refugio;
import com.example.demo.service.RefugioService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/refugios")
public class RefugioController {

    private final RefugioService refugioService;

    public RefugioController(RefugioService refugioService) {
        this.refugioService = refugioService;
    }

    @GetMapping
    public String mostrarRefugios(org.springframework.ui.Model model) {
        model.addAttribute("refugios", refugioService.obtenerTodos());
        return "refugios";
    }

    // 🔥 Endpoint para AJAX (JS)
    @PostMapping("/agregar")
    @ResponseBody
    public Refugio agregarRefugioJSON(@RequestBody RefugioDTO dto) {

        FabricaRefugio fabrica;

        switch (dto.getTipo()) {
            case "gatos":
                fabrica = new FabricaRefugioGatos();
                break;
            case "perros":
                fabrica = new FabricaRefugioPerros();
                break;
            default:
                throw new IllegalArgumentException("Tipo no válido: " + dto.getTipo());
        }

        // Crear el refugio usando Abstract Factory
        Refugio refugio = fabrica.crearRefugio(
                dto.getNombre(),
                dto.getDireccion(),
                dto.getCapacidad()
        );

        // Lo guardamos
        refugioService.agregarRefugio(refugio);

        // Devolver JSON al frontend
        return refugio;
    }
}

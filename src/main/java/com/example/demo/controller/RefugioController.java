package com.example.demo.controller;

import com.example.demo.absFactory.FabricaRefugio;
import com.example.demo.absFactory.FabricaRefugioGatos;
import com.example.demo.absFactory.FabricaRefugioPerros;
import com.example.demo.dto.RefugioDTO;
import com.example.demo.model.Refugio;
import com.example.demo.service.RefugioService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/refugios")
public class RefugioController {
    private final RefugioService refugioService;

    public RefugioController(RefugioService refugioService) {
        this.refugioService = refugioService;
    }

    // 🔥 Nuevo endpoint para devolver JSON (para JavaScript)
    @GetMapping(produces = "application/json")
    @ResponseBody
    public List<Refugio> obtenerRefugiosJSON() {
        return refugioService.obtenerTodos();
    }

    @GetMapping("/vista")
    public String mostrarRefugios(org.springframework.ui.Model model) {
        model.addAttribute("refugios", refugioService.obtenerTodos());
        return "refugios";
    }

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

        Refugio refugio = fabrica.crearRefugio(
                dto.getNombre(),
                dto.getDireccion(),
                dto.getCapacidad()
        );

        refugioService.agregarRefugio(refugio);
        return refugio;
    }
}

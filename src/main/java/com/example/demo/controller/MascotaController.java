package com.example.demo.controller;

import com.example.demo.model.Mascota;
import com.example.demo.service.RefugioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mascotas")
public class MascotaController {

    private final RefugioService refugioService;

    public MascotaController(RefugioService refugioService) {
        this.refugioService = refugioService;
    }

    @PostMapping("/registrar")
    public ResponseEntity<?> registrarMascota(
            @RequestBody Mascota mascota,
            @RequestParam String refugio) {

        try {
            refugioService.agregarMascotaARefugio(refugio, mascota);
            return ResponseEntity.ok("Mascota registrada en el refugio: " + refugio);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
}

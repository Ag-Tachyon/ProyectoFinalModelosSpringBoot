package com.example.demo.controller;

import com.example.demo.model.Mascota;
import com.example.demo.service.AdopcionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/adopciones")
public class AdopcionController {

    private final AdopcionService adopcionService;

    public AdopcionController(AdopcionService adopcionService) {
        this.adopcionService = adopcionService;
    }

    /**
     * Endpoint para adoptar una mascota.
     * * @param mascotaId ID de la mascota.
     * @param usuarioId ID del usuario que adopta.
     * @return La mascota con su nuevo estado "Adoptada".
     */
    @PostMapping("/adoptar")
    public ResponseEntity<?> adoptarMascota(
            @RequestParam String mascotaId,
            @RequestParam String usuarioId) {

        try {
            // Llama a la lógica de negocio centralizada en el servicio
            Mascota mascotaActualizada = adopcionService.procesarAdopcion(mascotaId, usuarioId);

            return ResponseEntity.ok(
                    String.format("¡Adopción exitosa! La mascota %s tiene ahora el estado: %s. Agregada al usuario %s.",
                            mascotaId,
                            mascotaActualizada.getEstado().getNombreEstado(),
                            usuarioId)
            );
        } catch (RuntimeException e) {
            // Manejo básico de errores (mascota no disponible, ID incorrecto, etc.)
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
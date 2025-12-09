package com.example.demo.controller;

import com.example.demo.model.Usuario;
import com.example.demo.service.NotificacionesService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notificaciones2")
public class NotificacionesController {

    private final NotificacionesService servicio;

    public NotificacionesController(NotificacionesService servicio) {
        this.servicio = servicio;
    }

    @PostMapping("/suscribir")
    public String suscribir(@RequestBody Usuario usuario) {
        servicio.addObserver(usuario);
        return "Usuario suscrito";
    }

    @PostMapping("/desuscribir")
    public String desuscribir(@RequestBody Usuario usuario) {
        servicio.removeObserver(usuario);
        return "Usuario desuscrito";
    }

    @GetMapping("/cantidad")
    public int cantidad() {
        return servicio.contarObservadores();
    }

    @PostMapping("/enviar")
    public String enviarNotificacion(@RequestParam String mensaje) {
        servicio.notifyObservers(mensaje);
        return "Notificaciones enviadas";
    }
}

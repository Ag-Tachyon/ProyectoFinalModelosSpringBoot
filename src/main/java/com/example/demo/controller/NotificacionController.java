package com.example.demo.controller;

import com.example.demo.observer.ObserverManager;
import com.example.demo.observer.UsuarioObserver;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/notificaciones")
public class NotificacionController {

    // 1. Centralizamos el acceso al Subject (Sujeto)
    private final ObserverManager observerManager = ObserverManager.getInstance();

    // 2. Este mapa almacena la referencia única a cada UsuarioObserver,
    //    usando el nombre como clave. Es esencial para evitar duplicados y para la desuscripción.
    private final Map<String, UsuarioObserver> usuariosRegistrados = new HashMap<>();

    // --- Endpoint de Suscripción ---
    @PostMapping("/suscribirse")
    public Map<String, Object> suscribirse(@RequestParam String nombre) {

        // **CORRECCIÓN CLAVE:** Comprobamos si el usuario ya existe en nuestro mapa.
        if (!usuariosRegistrados.containsKey(nombre)) {
            UsuarioObserver nuevo = new UsuarioObserver(nombre);
            usuariosRegistrados.put(nombre, nuevo);
            observerManager.addObserver(nuevo);
        }
        // Si ya existe, no hacemos nada (es idempotente).

        Map<String, Object> resp = new HashMap<>();
        resp.put("mensaje", "Usuario suscrito o ya existente!");
        resp.put("cantidad", observerManager.getCantidad());
        return resp;
    }

    // --- Endpoint de Desuscripción ---
    @PostMapping("/desuscribirse")
    public Map<String, Object> desuscribirse(@RequestParam String nombre) {

        UsuarioObserver usr = usuariosRegistrados.get(nombre);

        if (usr != null) {
            observerManager.removeObserver(usr);
            usuariosRegistrados.remove(nombre); // Lo removemos del mapa de referencias
        }

        Map<String, Object> resp = new HashMap<>();
        resp.put("mensaje", "Usuario desuscrito!");
        resp.put("cantidad", observerManager.getCantidad());
        return resp;
    }

    // --- Endpoint de Notificación ---
    @PostMapping("/enviar")
    public Map<String, Object> enviarMensaje(@RequestParam String mensaje) {
        observerManager.notifyObservers(mensaje);

        Map<String, Object> resp = new HashMap<>();
        resp.put("mensaje", "Notificación enviada!");
        resp.put("cantidad", observerManager.getCantidad());
        return resp;
    }

    // --- (Adición) Endpoint GET para obtener la cantidad (más RESTful) ---
    @GetMapping("/cantidad")
    public Map<String, Object> getCantidadObservadores() {
        Map<String, Object> resp = new HashMap<>();
        resp.put("cantidad", observerManager.getCantidad());
        return resp;
    }
}
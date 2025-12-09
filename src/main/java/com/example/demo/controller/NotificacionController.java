package com.example.demo.controller;


import com.example.demo.observer.ObserverManager;
import com.example.demo.observer.UsuarioObserver;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/notificaciones")
public class NotificacionController {

    private Map<String, UsuarioObserver> usuariosRegistrados = new HashMap<>();

    @PostMapping("/suscribirse")
    public Map<String, Object> suscribirse(@RequestParam String nombre) {
        UsuarioObserver nuevo = new UsuarioObserver(nombre);
        usuariosRegistrados.put(nombre, nuevo);
        ObserverManager.getInstance().addObserver(nuevo);

        Map<String, Object> resp = new HashMap<>();
        resp.put("mensaje", "Usuario suscrito!");
        resp.put("cantidad", ObserverManager.getInstance().getCantidad());
        return resp;
    }

    @PostMapping("/desuscribirse")
    public Map<String, Object> desuscribirse(@RequestParam String nombre) {

        UsuarioObserver usr = usuariosRegistrados.get(nombre);

        if (usr != null) {
            ObserverManager.getInstance().removeObserver(usr);
            usuariosRegistrados.remove(nombre);
        }

        Map<String, Object> resp = new HashMap<>();
        resp.put("mensaje", "Usuario eliminado!");
        resp.put("cantidad", ObserverManager.getInstance().getCantidad());
        return resp;
    }

    @PostMapping("/enviar")
    public Map<String, Object> enviarMensaje(@RequestParam String mensaje) {

        ObserverManager.getInstance().notifyObservers(mensaje);

        Map<String, Object> resp = new HashMap<>();
        resp.put("mensaje", "Notificación enviada!");
        resp.put("cantidad", ObserverManager.getInstance().getCantidad());
        return resp;
    }
}


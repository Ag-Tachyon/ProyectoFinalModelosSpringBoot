package com.example.demo.controller;

import com.example.demo.model.Mascota;
import com.example.demo.model.Refugio;
import com.example.demo.service.RefugioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/refugios")
public class RefugioController {

    private final RefugioService refugioService;

    public RefugioController(RefugioService refugioService) {
        this.refugioService = refugioService;
    }

    // ---------------------------------------------------------
    // LISTA DE REFUGIOS
    // ---------------------------------------------------------
    @GetMapping
    public String mostrarRefugios(Model model) {
        model.addAttribute("refugios", refugioService.obtenerTodos());
        return "refugios";
    }

    // ---------------------------------------------------------
    // DETALLE DE REFUGIO
    // ---------------------------------------------------------
    @GetMapping("/{nombre}")
    public String mostrarDetalleRefugio(@PathVariable String nombre, Model model) {
        Refugio refugio = refugioService.buscarPorNombre(nombre);
        if (refugio == null) return "redirect:/refugios?notfound=true";

        model.addAttribute("refugio", refugio);
        model.addAttribute("nuevaMascota", new Mascota());
        return "refugio_detalle";
    }

    // ---------------------------------------------------------
    // FORMULARIO NUEVA MASCOTA (si se usa aparte)
    // ---------------------------------------------------------
    @GetMapping("/{nombre}/mascotas/nuevo")
    public String mostrarFormNuevaMascota(@PathVariable String nombre, Model model) {
        Refugio refugio = refugioService.buscarPorNombre(nombre);
        if (refugio == null) return "redirect:/refugios";

        model.addAttribute("refugio", refugio);
        model.addAttribute("mascota", new Mascota());
        return "form_mascota";
    }

    // ---------------------------------------------------------
    // GUARDAR NUEVA MASCOTA
    // ---------------------------------------------------------
    @PostMapping("/{nombre}/mascotas/guardar")
    public String guardarMascotaEnRefugio(@PathVariable String nombre,
                                          @ModelAttribute Mascota mascota) {
        refugioService.agregarMascotaARefugio(nombre, mascota);
        return "redirect:/refugios/" + nombre;
    }

    // ---------------------------------------------------------
    // AGREGAR SEGUIDOR
    // ---------------------------------------------------------
    @PostMapping("/{nombre}/seguidores/agregar")
    public String agregarSeguidor(@PathVariable String nombre,
                                  @RequestParam String nombreUsuario) {
        refugioService.agregarSeguidorARefugio(nombre, nombreUsuario);
        return "redirect:/refugios/" + nombre;
    }

    // ---------------------------------------------------------
    // CREAR EVENTO (Observer)
    // ---------------------------------------------------------
    @PostMapping("/{nombre}/crear-evento")
    public String crearEvento(@PathVariable String nombre) {
        // Aquí va tu Observer real
        // notificador.notificar(r.getSeguidores(), "Nuevo evento...");

        return "redirect:/refugios/" + nombre + "?evento=ok";
    }

    // ---------------------------------------------------------
    // FORM CREAR REFUGIO
    // ---------------------------------------------------------
    @GetMapping("/crear")
    public String mostrarFormCrear(Model model) {
        model.addAttribute("refugio", new Refugio() {
            @Override
            public String mostrarInfoRefugio() { return null; }

            @Override
            public void registrarMascota(Mascota mascota) {}
        });
        return "crear_refugio";
    }

    // ---------------------------------------------------------
    // GUARDAR REFUGIO
    // ---------------------------------------------------------
    @PostMapping("/crear")
    public String guardarRefugio(@ModelAttribute Refugio refugio) {
        refugioService.agregarRefugio(refugio);
        return "redirect:/refugios";
    }
}

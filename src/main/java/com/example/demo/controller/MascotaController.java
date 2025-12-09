package com.example.demo.controller;

import com.example.demo.builder.*;
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
            @RequestBody MascotaDTO mascotaDTO,
            @RequestParam String refugio) {

        try {
            Mascota mascota = crearMascotaConBuilder(mascotaDTO);
            refugioService.agregarMascotaARefugio(refugio, mascota);
            return ResponseEntity.ok("Mascota registrada en el refugio: " + refugio);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    private Mascota crearMascotaConBuilder(MascotaDTO dto) {
        MascotaBuilder builder;

        // Determinar qué builder usar según el tipo de mascota
        if ("gato".equalsIgnoreCase(dto.getTipo())) {
            builder = new GatoBuilder();
        } else if ("perro".equalsIgnoreCase(dto.getTipo())) {
            builder = new PerroBuilder();
        } else {
            throw new IllegalArgumentException("Tipo de mascota no válido: " + dto.getTipo());
        }

        Director director = new Director(builder);

        // Construir la mascota completa
        director.construirMascotaCompleta(
                dto.getNombre(),
                dto.getRaza(),
                dto.getEdad(),
                dto.getSexo(),
                dto.getSize(),
                dto.getVacunas(),
                dto.isEstadoInicial()
        );

        return builder.build();
    }
}

// DTO para recibir los datos de la mascota desde el frontend
class MascotaDTO {
    private String nombre;
    private String raza;
    private int edad;
    private String sexo;
    private String size;
    private String vacunas;
    private String tipo; // "gato" o "perro"
    private boolean estadoInicial;
    private String imagenUrl; // Nuevo campo

    // Getters y Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getRaza() { return raza; }
    public void setRaza(String raza) { this.raza = raza; }

    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad; }

    public String getSexo() { return sexo; }
    public void setSexo(String sexo) { this.sexo = sexo; }

    public String getSize() { return size; }
    public void setSize(String size) { this.size = size; }

    public String getVacunas() { return vacunas; }
    public void setVacunas(String vacunas) { this.vacunas = vacunas; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public boolean isEstadoInicial() { return estadoInicial; }
    public void setEstadoInicial(boolean estadoInicial) { this.estadoInicial = estadoInicial; }

    public String getImagenUrl() { return imagenUrl; }
    public void setImagenUrl(String imagenUrl) { this.imagenUrl = imagenUrl; }
}
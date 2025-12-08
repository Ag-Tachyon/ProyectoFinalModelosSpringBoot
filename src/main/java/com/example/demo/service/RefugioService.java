package com.example.demo.service;

import com.example.demo.model.Mascota;
import com.example.demo.model.Refugio;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class RefugioService {

    private final String FILE_PATH = "refugios.json";
    private List<Refugio> refugios = new ArrayList<>();
    private final ObjectMapper mapper = new ObjectMapper();

    public RefugioService() {
        cargar();
    }

    // ---------------------------------------------------------
    // Cargar JSON
    // ---------------------------------------------------------
    private void cargar() {
        try {
            File file = new File(FILE_PATH);
            if (file.exists()) {
                refugios = mapper.readValue(file, new TypeReference<List<Refugio>>() {});
            }
        } catch (Exception e) {
            e.printStackTrace();
            refugios = new ArrayList<>();
        }
    }

    // ---------------------------------------------------------
    // Guardar JSON
    // ---------------------------------------------------------
    private void guardar() {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(FILE_PATH), refugios);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ---------------------------------------------------------
    // CRUD
    // ---------------------------------------------------------
    public List<Refugio> obtenerTodos() {
        return refugios;
    }

    public void agregarRefugio(Refugio refugio) {
        refugios.add(refugio);
        guardar();
    }

    public Refugio buscarPorNombre(String nombre) {
        return refugios.stream()
                .filter(r -> r.getNombre().equalsIgnoreCase(nombre))
                .findFirst()
                .orElse(null);
    }

    public void agregarMascotaARefugio(String nombreRefugio, Mascota mascota) {
        Refugio r = buscarPorNombre(nombreRefugio);
        if (r != null) {
            r.getMascotas().add(mascota);
            guardar();
        }
    }

    public void agregarSeguidorARefugio(String nombreRefugio, String nombreUsuario) {
        Refugio r = buscarPorNombre(nombreRefugio);
        if (r != null && !r.getSeguidores().contains(nombreUsuario)) {
            r.getSeguidores().add(nombreUsuario);
            guardar();
        }
    }
}

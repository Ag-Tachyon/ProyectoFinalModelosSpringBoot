package com.example.demo.service;

import com.example.demo.model.Mascota;
import com.example.demo.model.Refugio;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class RefugioService {

    private final String FILE_PATH = "src/main/resources/data/refugios.data";

    private List<Refugio> refugios = new ArrayList<>();

    public RefugioService() {
        cargar();
    }

    // ---------------------------------------------------------
    // CARGAR ARCHIVO .data
    // ---------------------------------------------------------
    @SuppressWarnings("unchecked")
    private void cargar() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            refugios = (List<Refugio>) ois.readObject();
            System.out.println("RefugioService: datos cargados.");
        } catch (FileNotFoundException e) {
            System.out.println("Archivo de refugios no encontrado, creando lista vacía...");
            refugios = new ArrayList<>();
            guardar(); // crea el archivo
        } catch (Exception e) {
            e.printStackTrace();
            refugios = new ArrayList<>();
        }
    }

    // ---------------------------------------------------------
    // GUARDAR ARCHIVO .data
    // ---------------------------------------------------------
    private void guardar() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(refugios);
            System.out.println("RefugioService: archivo guardado.");
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

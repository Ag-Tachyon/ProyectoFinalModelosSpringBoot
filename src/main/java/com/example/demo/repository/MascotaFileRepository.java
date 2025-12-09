package com.example.demo.repository;

import com.example.demo.model.Mascota;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class MascotaFileRepository {

    private final String FILE_PATH = "mascotas.data";
    private List<Mascota> mascotas = new ArrayList<>();

    public MascotaFileRepository() {
        cargarArchivo();
    }

    // --- Métodos de Persistencia Interna ---

    @SuppressWarnings("unchecked")
    private void cargarArchivo() {
        File file = new File(FILE_PATH);

        if (!file.exists()) {
            guardarArchivo();
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            mascotas = (List<Mascota>) ois.readObject();
        } catch (Exception e) {
            mascotas = new ArrayList<>();
        }
    }

    private void guardarArchivo() {
        try {
            // Aseguramos que la carpeta 'data' exista (si es necesario)
            File carpeta = new File("data");
            if (!carpeta.exists()) {
                carpeta.mkdirs();
            }

            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
                oos.writeObject(mascotas);
            }

        } catch (IOException e) {
            throw new RuntimeException("Error guardando mascotas.data", e);
        }
    }

    /**
     * Hace persistente la lista actual en memoria al archivo.
     * Útil para cuando se modifica un objeto referenciado.
     */
    public synchronized void guardarCambios() {
        guardarArchivo();
    }

    // --- Métodos de Acceso a Datos (DAO) ---

    /**
     * Guarda una nueva mascota o actualiza una existente (si ya tiene ID).
     * @param mascota La mascota a guardar.
     */
    public synchronized void save(Mascota mascota) {
        // En una app real, aquí asignarías un ID si es nueva.
        // Para este ejemplo, simplificamos asumiendo que ya tiene un ID o lo manejas externamente.

        // Lógica de actualización simple: si existe, la reemplaza; si no, la añade.
        Mascota existing = findById(mascota.getId());
        if (existing != null) {
            // Encuentra el índice y reemplaza
            int index = mascotas.indexOf(existing);
            if (index != -1) {
                mascotas.set(index, mascota);
            }
        } else {
            // Es una nueva mascota
            mascotas.add(mascota);
        }

        guardarArchivo(); // Persiste la lista después del cambio.
    }

    /**
     * Busca una mascota por su ID.
     * @param id El ID de la mascota.
     * @return La mascota encontrada o null.
     */
    public Mascota findById(String id) {
        // Asumiendo que Mascota tiene un método getId() que devuelve String
        return mascotas.stream()
                .filter(m -> m.getId().equalsIgnoreCase(id))
                .findFirst()
                .orElse(null);
    }

    /**
     * Devuelve todas las mascotas.
     */
    public List<Mascota> findAll() {
        return mascotas;
    }
}
package com.example.demo.singleton;

import com.example.demo.model.Mascota;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

// Implementa la interfaz DataManager para el tipo Mascota
public class MascotasData implements DataManager<Mascota> {

    // 1. Instancia Estática Privada (Singleton)
    private static MascotasData administrador;

    // Ruta del archivo de persistencia
    private static final String RUTA_MASCOTAS = "mascotas_data.bin";

    // 2. Constructor Privado
    private MascotasData() {
        System.out.println("MascotasData: Instancia Singleton creada.");
    }

    // 3. Método Estático de Acceso Global
    public static MascotasData getAdministrador() {
        if (administrador == null) {
            administrador = new MascotasData();
        }
        return administrador;
    }

    // --- Implementación de DataManager con Persistencia ---

    /**
     * Lee y retorna la lista de mascotas desde el archivo binario.
     * @return Una lista de objetos Mascota.
     */
    @Override
    public List<Mascota> leerDatos() {
        // Lógica de Deserialización
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(RUTA_MASCOTAS))) {
            System.out.println("✅ MascotasData: Leyendo datos desde archivo...");
            // Se asume que el archivo contiene una List<Mascota> serializada
            return (List<Mascota>) ois.readObject();
        } catch (FileNotFoundException e) {
            // Caso inicial: el archivo no existe, retornamos una lista vacía
            System.out.println("⚠️ Archivo de mascotas no encontrado. Creando lista vacía.");
            return new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("🔴 Error al leer las mascotas: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    /**
     * Guarda la lista actual de mascotas en el archivo binario.
     * @param datos La lista de Mascota a guardar.
     */
    @Override
    public void guardarDatos(List<Mascota> datos) {
        // Lógica de Serialización
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(RUTA_MASCOTAS))) {
            oos.writeObject(datos);
            System.out.println("💾 MascotasData: " + datos.size() + " mascotas guardadas exitosamente.");
        } catch (IOException e) {
            System.err.println("🔴 Error al guardar las mascotas: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
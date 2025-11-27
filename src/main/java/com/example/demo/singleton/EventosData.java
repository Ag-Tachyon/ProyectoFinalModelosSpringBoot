package com.example.demo.singleton;

import com.example.demo.model.Evento;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
// Implementa la interfaz DataManager para el tipo Evento

public class EventosData implements DataManager<Evento> {

    // 1. Instancia Estática Privada (Singleton)
    private static EventosData administrador;

    private static final String RUTA_EVENTOS = "eventos_data.bin";
    // 2. Constructor Privado para evitar instanciación externa
    private EventosData EventosData() {
        if (administrador == null) {
            administrador = new EventosData();
        }
        return administrador;
    }

    // 3. Método Estático de Acceso Global
    public static EventosData getAdministrador() {
        if (administrador == null) {
            administrador = new EventosData();
        }
        return administrador;
    }

    // --- Implementación de DataManager ---
    @Override
    public List<Evento> leerDatos() {
        // 1. Lógica de Deserialización
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(RUTA_EVENTOS))) {
            System.out.println("✅ EventosData: Leyendo datos desde archivo...");
            // Retorna la lista de eventos guardada
            return (List<Evento>) ois.readObject();
        } catch (FileNotFoundException e) {
            // Es la primera vez que se ejecuta, el archivo no existe
            System.out.println("⚠️ Archivo de eventos no encontrado. Creando lista vacía.");
            return new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("🔴 Error al leer los eventos: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public void guardarDatos(List<Evento> datos) {
        // 2. Lógica de Serialización
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(RUTA_EVENTOS))) {
            oos.writeObject(datos);
            System.out.println("💾 EventosData: " + datos.size() + " eventos guardados exitosamente.");
        } catch (IOException e) {
            System.err.println("🔴 Error al guardar los eventos: " + e.getMessage());
            e.printStackTrace();
        }
    }
    // Método helper para añadir un solo evento
    public void agregarEvento(Evento evento) {
        List<Evento> eventosActuales = leerDatos();
        eventosActuales.add(evento);
        guardarDatos(eventosActuales);
        System.out.println("EventosData: Evento agregado y guardado.");
    }
}
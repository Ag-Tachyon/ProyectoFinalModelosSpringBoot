package com.example.demo.singleton;

import com.example.demo.model.Evento;
import java.util.ArrayList;
import java.util.List;

// Implementa la interfaz DataManager para el tipo Evento
public class EventosData implements DataManager<Evento> {

    // 1. Instancia Estática Privada (Singleton)
    private static EventosData administrador;

    // Simulación de la ruta donde se almacenarían los eventos
    private final String rutaEventos = "data/eventos.dat";

    // 2. Constructor Privado para evitar instanciación externa
    private EventosData() {
        // Inicialización de recursos o carga de datos iniciales
        System.out.println("EventosData: Instancia Singleton creada.");
    }

    // 3. Método Estático de Acceso Global
    public static EventosData getAdministrador() {
        if (administrador == null) {
            administrador = new EventosData();
        }
        return administrador;
    }

    // --- Implementación de DataManager ---

    /**
     * Lee y retorna la lista de eventos.
     * @return Una lista de objetos Evento.
     */
    @Override
    public List<Evento> leerDatos() {
        System.out.println("EventosData: Leyendo lista de eventos desde: " + rutaEventos);

        // Simulación de la lógica de deserialización o consulta a la DB.

        // Creación de una lista dummy para propósitos de prueba
        List<Evento> eventos = new ArrayList<>();
        // Asumiendo que la clase Evento tiene un constructor (String descripcion, String fecha)
        // eventos.add(new Evento("Vacunación Masiva", "2025-12-01"));
        // eventos.add(new Evento("Jornada de Adopción", "2025-12-15"));

        return eventos;
    }

    /**
     * Guarda la lista de eventos en el medio de persistencia.
     * @param datos La lista de Eventos a guardar.
     */
    @Override
    public void guardarDatos(List<Evento> datos) {
        System.out.println("EventosData: Guardando " + datos.size() + " eventos en: " + rutaEventos);
        // Aquí iría la lógica real de serialización, escritura en archivo o persistencia en DB.
    }

    // Método helper para añadir un solo evento
    public void agregarEvento(Evento evento) {
        List<Evento> eventosActuales = leerDatos();
        eventosActuales.add(evento);
        guardarDatos(eventosActuales);
        System.out.println("EventosData: Evento agregado y guardado.");
    }
}
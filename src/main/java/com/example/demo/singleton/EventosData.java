package com.example.demo.singleton;

import com.example.demo.model.Evento;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EventosData implements DataManager<Evento> {

    private static EventosData administrador;

    private static final String RUTA_EVENTOS = "eventos_data.data";

    private EventosData() {
        System.out.println("EventosData: Instancia Singleton creada.");
    }

    public static EventosData getAdministrador() {
        if (administrador == null) {
            administrador = new EventosData();
        }
        return administrador;
    }

    @Override
    public List<Evento> leerDatos() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(RUTA_EVENTOS))) {
            System.out.println("✅ EventosData: Leyendo datos desde archivo...");
            return (List<Evento>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("⚠️ Archivo de eventos no encontrado. Creando lista vacía.");
            return new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("🔴 Error al leer los eventos: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public void guardarDatos(List<Evento> datos) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(RUTA_EVENTOS))) {
            oos.writeObject(datos);
            System.out.println("💾 EventosData: " + datos.size() + " eventos guardados.");
        } catch (IOException e) {
            System.err.println("🔴 Error al guardar los eventos: " + e.getMessage());
        }
    }

    public void agregarEvento(Evento evento) {
        List<Evento> lista = leerDatos();
        lista.add(evento);
        guardarDatos(lista);
        System.out.println("EventosData: Evento agregado.");
    }
}

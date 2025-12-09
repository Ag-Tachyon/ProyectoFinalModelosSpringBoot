package com.example.demo.singleton;

import com.example.demo.model.Mascota;
import com.example.demo.model.Refugio;
import com.example.demo.model.Usuario;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RefugiosData implements DataManager<Refugio> {

    private static RefugiosData administrador;
    private static final String RUTA_REFUGIOS = "refugios_data.data";

    private RefugiosData() {
        System.out.println("RefugiosData: Instancia Singleton creada.");
    }

    public static RefugiosData getAdministrador() {
        if (administrador == null) {
            administrador = new RefugiosData();
        }
        return administrador;
    }

    @Override
    public List<Refugio> leerDatos() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(RUTA_REFUGIOS))) {
            System.out.println("✅ RefugioData: Leyendo mascotas...");
            return (List<Refugio>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("⚠️ Archivo de refugio no encontrado. Creando lista vacía.");
            return new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("🔴 Error al leer refugio: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public void guardarDatos(List<Refugio> datos) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(RUTA_REFUGIOS))) {
            oos.writeObject(datos);
            System.out.println("💾 RefugiosData: " + datos.size() + " refugios guardadas.");
        } catch (IOException e) {
            System.err.println("🔴 Error al guardar refugio: " + e.getMessage());
        }
    }

    }


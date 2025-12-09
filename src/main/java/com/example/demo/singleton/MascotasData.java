package com.example.demo.singleton;

import com.example.demo.model.Mascota;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MascotasData implements DataManager<Mascota> {

    private static MascotasData administrador;

    private static final String RUTA_MASCOTAS = "mascotas_data.data";

    private MascotasData() {
        System.out.println("MascotasData: Instancia Singleton creada.");
    }

    public static MascotasData getAdministrador() {
        if (administrador == null) {
            administrador = new MascotasData();
        }
        return administrador;
    }

    @Override
    public List<Mascota> leerDatos() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(RUTA_MASCOTAS))) {
            System.out.println("✅ MascotasData: Leyendo mascotas...");
            return (List<Mascota>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("⚠️ Archivo de mascotas no encontrado. Creando lista vacía.");
            return new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("🔴 Error al leer mascotas: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public void guardarDatos(List<Mascota> datos) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(RUTA_MASCOTAS))) {
            oos.writeObject(datos);
            System.out.println("💾 MascotasData: " + datos.size() + " mascotas guardadas.");
        } catch (IOException e) {
            System.err.println("🔴 Error al guardar mascotas: " + e.getMessage());
        }
    }
}

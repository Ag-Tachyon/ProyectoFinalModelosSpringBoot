package com.example.demo.service;

import com.example.demo.model.Mascota;
import com.example.demo.model.Refugio;
import com.example.demo.singleton.RefugiosData;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RefugioService {
    private RefugiosData refugiosData = RefugiosData.getAdministrador();
    private final List<Refugio> refugios = refugiosData.leerDatos();

    public List<Refugio> obtenerTodos() {
        return refugiosData.leerDatos();
    }

    public void agregarRefugio(Refugio refugio) {
        refugios.add(refugio);
        refugiosData.guardarDatos(refugios);
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
            // Verificar que no exceda la capacidad
            if (r.getMascotas().size() < r.getCapacidad()) {
                r.getMascotas().add(mascota);
                refugiosData.guardarDatos(refugios);
            } else {
                throw new IllegalStateException("El refugio ha alcanzado su capacidad máxima");
            }
        } else {
            throw new IllegalArgumentException("Refugio no encontrado: " + nombreRefugio);
        }
    }

    public void agregarSeguidorARefugio(String nombreRefugio, String nombreUsuario) {
        Refugio r = buscarPorNombre(nombreRefugio);
        if (r != null && !r.getSeguidores().contains(nombreUsuario)) {
            r.getSeguidores().add(nombreUsuario);
            refugiosData.guardarDatos(refugios);
        }
    }
}
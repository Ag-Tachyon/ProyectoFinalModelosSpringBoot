package com.example.demo.state;

import com.example.demo.model.Mascota;

public class EstadoDisponible implements EstadoMascota {

    @Override
    public void adoptarMascota(Mascota mascota) {
        System.out.println(" ha sido adoptada.");
        // Cambia el estado interno de la Mascota al siguiente estado
        mascota.setEstado(new EstadoAdoptada());
    }

    @Override
    public void devolverMascota(Mascota mascota) {
        System.out.println(" ya está disponible. No se puede devolver.");
        // No hay cambio de estado
    }

    @Override
    public String getNombreEstado() {
        return "Disponible";
    }

    // ... otros métodos de la interfaz
}
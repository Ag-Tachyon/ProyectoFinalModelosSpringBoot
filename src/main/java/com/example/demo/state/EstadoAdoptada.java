package com.example.demo.state;

import com.example.demo.model.Mascota;

import java.io.Serializable;

public class EstadoAdoptada implements EstadoMascota, Serializable {
    @Override
    public void adoptarMascota(Mascota mascota) {
        //No aplica
    }

    @Override
    public void DisponibleMascota(Mascota mascota) {
        mascota.setEstado(new EstadoDisponible());
        mascota.setAdoptado(false);
    }

    @Override
    public String getNombreEstado() {
        return "Adoptada";
    }
}

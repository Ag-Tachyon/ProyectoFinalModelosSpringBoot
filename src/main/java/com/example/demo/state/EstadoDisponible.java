package com.example.demo.state;

import com.example.demo.model.Mascota;

import java.io.Serializable;

public class EstadoDisponible implements EstadoMascota, Serializable {

    @Override
    public void adoptarMascota(Mascota mascota) {
        mascota.setEstado(new EstadoAdoptada());
        mascota.setAdoptado(true);
    }

    @Override
    public void DisponibleMascota(Mascota mascota) {
        //No aplica
    }

    @Override
    public String getNombreEstado() {
        return "Disponible";
    }

}
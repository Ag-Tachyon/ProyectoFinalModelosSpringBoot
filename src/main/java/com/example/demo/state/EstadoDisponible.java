package com.example.demo.state;

import com.example.demo.model.Mascota;

public class EstadoDisponible implements EstadoMascota {

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
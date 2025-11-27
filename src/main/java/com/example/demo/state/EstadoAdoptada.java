package com.example.demo.state;

import com.example.demo.model.Mascota;

public class EstadoAdoptada implements EstadoMascota {
    @Override
    public void adoptarMascota(Mascota mascota) {

    }

    @Override
    public void devolverMascota(Mascota mascota) {
        mascota.setEstado(new EstadoDevuelta());
    }

    @Override
    public String getNombreEstado() {
        return "Adoptada";
    }
}

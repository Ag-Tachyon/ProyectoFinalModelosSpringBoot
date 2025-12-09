package com.example.demo.state;

import com.example.demo.model.Mascota;

import java.io.Serializable;

public interface EstadoMascota extends Serializable {
    void adoptarMascota(Mascota mascota);

    void DisponibleMascota(Mascota mascota);

    String getNombreEstado();

}

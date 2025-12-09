package com.example.demo.state;

import com.example.demo.model.Mascota;

public interface EstadoMascota {
    void adoptarMascota(Mascota mascota);

    void DisponibleMascota(Mascota mascota);

    String getNombreEstado();

}

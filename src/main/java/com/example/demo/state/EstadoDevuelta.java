package com.example.demo.state;

import com.example.demo.model.Mascota;

public class EstadoDevuelta implements EstadoMascota {
    @Override
    public void adoptarMascota(Mascota mascota) {
        mascota.setEstado(new EstadoAdoptada());
    }

    @Override
    public void devolverMascota(Mascota mascota) {
        System.out.println("❌ La mascota  ya fue devuelta. Su estado actual es: Devuelta.");
    }

    @Override
    public String getNombreEstado() {
        return "Devuelta";
    }
}

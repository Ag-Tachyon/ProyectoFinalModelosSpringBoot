package com.example.demo.strategy;

import com.example.demo.model.Mascota;

public class FiltroEdad implements InterfazFiltroMascotas {
    private int edadMinima;
    public FiltroEdad(int edadMinima) {
        this.edadMinima = edadMinima;
    }
    @Override
    public boolean cumple(Mascota mascota) {
        if(mascota.getEdad()>=edadMinima){
            return true;
        }
        return false;
    }
}

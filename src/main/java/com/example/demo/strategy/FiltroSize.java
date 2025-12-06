package com.example.demo.strategy;

import com.example.demo.model.Mascota;

public class FiltroSize implements InterfazFiltroMascotas {
    private String Size;
    public FiltroSize(String Size) {
        this.Size = Size;
    }
    @Override
    public boolean cumple(Mascota mascota) {
        if(mascota.getSize().equals(Size)){
            return true;
        }
        return false;
    }

}

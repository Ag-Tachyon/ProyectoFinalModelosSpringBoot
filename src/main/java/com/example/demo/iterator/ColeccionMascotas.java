package com.example.demo.iterator;

import com.example.demo.strategy.InterfazFiltroMascotas;

public interface ColeccionMascotas {
    public IteradorMascota crearIterador(InterfazFiltroMascotas filtro);
}

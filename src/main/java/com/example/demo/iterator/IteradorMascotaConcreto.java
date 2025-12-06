package com.example.demo.iterator;

import com.example.demo.model.Mascota;
import com.example.demo.strategy.InterfazFiltroMascotas;

import java.util.ArrayList;
import java.util.Iterator;

public class IteradorMascotaConcreto implements IteradorMascota {
        private ArrayList<Mascota> listaMascotas;
        private InterfazFiltroMascotas filtro;
        private int posicion;

    public IteradorMascotaConcreto(ArrayList<Mascota> mascotas, InterfazFiltroMascotas filtro, int posicion) {
    this.listaMascotas = mascotas;
    this.filtro = filtro;
    this.posicion = posicion;
    }
    @Override
    public Boolean hasNext() {
        while (posicion<listaMascotas.size()) {
            Mascota  m = listaMascotas.get(posicion);
            if (filtro.cumple(m)){
                return true;
            }
            posicion++;
        }
        return false;
    }

    @Override
    public Mascota next() {
        if(hasNext()){
            return listaMascotas.get(posicion++);
        }
        return null;
    }
}

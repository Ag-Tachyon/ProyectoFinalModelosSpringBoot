package com.example.demo.absFactory;

import com.example.demo.model.Refugio;
import com.example.demo.model.RefugioGatos;

public class FabricaRefugioGatos implements FabricaRefugio {
    @Override
    public Refugio crearRefugio(String nombre, String direccion, int capacidad) {
        return new RefugioGatos(nombre, direccion, capacidad);
    }
}

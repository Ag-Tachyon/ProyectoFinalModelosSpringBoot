package com.example.demo.absFactory;

import com.example.demo.model.Refugio;
import com.example.demo.model.RefugioPerros;

public class FabricaRefugioPerros implements FabricaRefugio {
    @Override
    public Refugio crearRefugio(String nombre, String direccion, int capacidad) {
        return new RefugioPerros(nombre, direccion, capacidad);
    }
}

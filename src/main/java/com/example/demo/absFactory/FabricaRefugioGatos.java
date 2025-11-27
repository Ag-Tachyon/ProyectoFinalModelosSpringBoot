package com.example.demo.absFactory;

import com.example.demo.model.Refugio;
import com.example.demo.model.RefugioGatos; // Producto Concreto

public class FabricaRefugioGatos implements FabricaRefugio {
    @Override
    public Refugio crearRefugio(String nombre, String direccion, int capacidad) {
        // Devuelve el producto concreto (RefugioGatos) que hereda de Refugio.
        return new RefugioGatos(nombre, direccion, capacidad);
    }
}
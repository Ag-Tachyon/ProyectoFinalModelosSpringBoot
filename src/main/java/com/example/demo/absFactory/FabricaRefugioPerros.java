package com.example.demo.absFactory;

import com.example.demo.model.Refugio;
import com.example.demo.model.RefugioPerros; // Producto Concreto

public class FabricaRefugioPerros implements FabricaRefugio {
    @Override
    public Refugio crearRefugio(String nombre, String direccion, int capacidad) {
        // Devuelve el producto concreto (RefugioPerros) que hereda de Refugio.
        return new RefugioPerros(nombre, direccion, capacidad);
    }
}
package com.example.demo.absFactory;

import com.example.demo.model.Refugio;

public interface FabricaRefugio {
    Refugio crearRefugio(String nombre, String direccion, int capacidad);
}

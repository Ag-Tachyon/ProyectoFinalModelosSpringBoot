package com.example.demo.builder;

import com.example.demo.model.Mascota;

public interface MascotaBuilder {
    void reset();
    void setNombre(String nombre);
    void setRaza(String raza);
    void setEdad(int edad);
    void setSexo(String sexo);
    void setTarjetaVacunas(String vacunas);
    void setEstadoInicial(boolean estado);
    void setSize(String size);
    void setImagenUrl(String imagenUrl); // Nuevo método

    // Método para obtener el producto final
    Mascota build();
}
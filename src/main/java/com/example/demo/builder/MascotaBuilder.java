package com.example.demo.builder;

import com.example.demo.model.Mascota;
import com.example.demo.state.EstadoMascota;

public interface MascotaBuilder {
    void reset();
    void setNombre(String nombre);
    void setRaza(String raza);
    void setEdad(int edad);
    void setSexo(String sexo);
    void setTarjetaVacunas(String vacunas);
    void setHistorial(String historial);
    void setEstadoInicial(EstadoMascota estado); // Nuevo método
    void setSize(String mediano);

    // Método para obtener el producto final
    Mascota build();

}

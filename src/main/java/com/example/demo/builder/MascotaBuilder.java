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
    void setEstadoInicial(boolean estado);
    void setSize(String mediano);

    // Metodo para obtener el producto final
    Mascota build();

}

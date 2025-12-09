package com.example.demo.builder;

import com.example.demo.state.EstadoMascota; // Asumiendo que has creado el paquete state
import java.util.List;

public class Director {
    private MascotaBuilder builder;

    public Director(MascotaBuilder builder) {
        this.builder = builder;
    }

    public void changeBuilder(MascotaBuilder newBuilder) {
        this.builder = newBuilder;
    }

    public void construirMascotaCompleta(
            String nombre,
            String raza,
            int edad,
            String sexo,
            String size,
            String vacunas,
            boolean estadoInicial) {

        builder.reset();
        builder.setNombre(nombre);
        builder.setRaza(raza);
        builder.setEdad(edad);
        builder.setSexo(sexo);
        builder.setSize(size);
        builder.setTarjetaVacunas(vacunas);
        builder.setEstadoInicial(estadoInicial);
    }
}
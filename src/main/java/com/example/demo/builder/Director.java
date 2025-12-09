package com.example.demo.builder;

import com.example.demo.model.Mascota;
import com.example.demo.state.EstadoMascota;

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
            boolean estadoInicial,
            String imagenUrl) { // Nuevo parámetro

        builder.reset();
        builder.setNombre(nombre);
        builder.setRaza(raza);
        builder.setEdad(edad);
        builder.setSexo(sexo);
        builder.setSize(size);
        builder.setTarjetaVacunas(vacunas);
        builder.setEstadoInicial(estadoInicial);
        builder.setImagenUrl(imagenUrl); // Nuevo método
    }

    // Método sobrecargado para compatibilidad
    public void construirMascotaCompleta(
            String nombre,
            String raza,
            int edad,
            String sexo,
            String size,
            String vacunas,
            boolean estadoInicial) {

        construirMascotaCompleta(nombre, raza, edad, sexo, size,
                vacunas, estadoInicial, "");
    }
}
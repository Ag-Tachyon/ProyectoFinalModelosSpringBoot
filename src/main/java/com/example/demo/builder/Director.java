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

    public void construirMascotaSimple(String nombre, int edad) {
        builder.reset();
        builder.setNombre(nombre);
        builder.setEdad(edad);
        builder.setSexo("Desconocido");
        builder.setSize("Mediano");
    }

    public void construirMascotaParaAdopcion(String nombre, String raza, int edad, String size, EstadoMascota estadoInicial) {
        builder.reset();
        builder.setNombre(nombre);
        builder.setRaza(raza);
        builder.setEdad(edad);
        builder.setSize(size);
        builder.setSexo("Desconocido");
        builder.setEstadoInicial(estadoInicial);
    }


    public void construirMascotaCompleta(
            String nombre,
            String raza,
            int edad,
            String sexo,
            String size,
            String vacunas,
            EstadoMascota estadoInicial) {

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
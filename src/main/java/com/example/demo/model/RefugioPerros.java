package com.example.demo.model;

public class RefugioPerros extends Refugio {

    public RefugioPerros(String nombre, String direccion, int capacidad) {
        super(nombre, direccion, capacidad);
    }

    @Override
    public String mostrarInfoRefugio() {
        return "Refugio especializado en PERROS 🐶";
    }

    @Override
    public void registrarMascota(Mascota mascota) {
        getMascotas().add(mascota);
    }
}

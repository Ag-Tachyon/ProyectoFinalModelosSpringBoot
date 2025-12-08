package com.example.demo.model;

public class RefugioGatos extends Refugio {

    public RefugioGatos(String nombre, String direccion, int capacidad) {
        super(nombre, direccion, capacidad);
    }

    @Override
    public String mostrarInfoRefugio() {
        return "Refugio especializado en GATOS 🐱";
    }

    @Override
    public void registrarMascota(Mascota mascota) {
        getMascotas().add(mascota);
    }
}

package com.example.demo.model;

public class RefugioGatos extends Refugio {

    public RefugioGatos(String nombre, String direccion, int capacidad) {
        super(nombre, direccion, capacidad);
        System.out.println("Se ha creado un Refugio de Gatos: " + nombre);
    }

    @Override
    public String mostrarInfoRefugio() {
        return "Refugio: " + this.nombre + ", Dirección: " + this.direccion + ", Capacidad: " + this.capacidad;
    }

    @Override
    public void registrarMascota(Mascota mascota) {
        return;
    }
}

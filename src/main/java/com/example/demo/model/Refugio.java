package com.example.demo.model;

public abstract class Refugio {

    public String nombre;
    public String direccion;
    public int capacidad;
    public Mascota mascota;


    // Constructor para inicializar atributos
    public Refugio(String nombre, String direccion, int capacidad) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.capacidad = capacidad;
    }

    public abstract String mostrarInfoRefugio();
    public abstract void registrarMascota(Mascota mascota);
}
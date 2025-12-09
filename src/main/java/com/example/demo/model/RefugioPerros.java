package com.example.demo.model;

import com.example.demo.visitor.RefugioElement;
import com.example.demo.visitor.RefugioVisitor;

import java.io.Serializable;

public class RefugioPerros extends Refugio implements RefugioElement, Serializable {

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

    @Override
    public void aceptar(RefugioVisitor refugioVisitor) {
        refugioVisitor.visitar(this);
    }
}

package com.example.demo.model;

import com.example.demo.visitor.RefugioElement;
import com.example.demo.visitor.RefugioVisitor;

public class RefugioPerros extends Refugio implements RefugioElement {

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

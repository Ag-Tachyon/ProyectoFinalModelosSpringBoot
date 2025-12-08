package com.example.demo.model;

import com.example.demo.visitor.RefugioElement;
import com.example.demo.visitor.RefugioVisitor;

public class RefugioGatos extends Refugio implements RefugioElement {

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

    @Override
    public void aceptar(RefugioVisitor refugioVisitor) {
        refugioVisitor.visitar(this);
    }
}

package com.example.demo.builder;

import com.example.demo.model.Mascota;
import com.example.demo.state.EstadoMascota;

public class PerroBuilder implements MascotaBuilder {
    private Mascota mascota;

    @Override
    public void reset() {
        this.mascota = new Mascota();
    }

    @Override
    public void setNombre(String nombre) {
        mascota.setNombre(nombre);
    }

    @Override
    public void setRaza(String raza) {
        mascota.setRaza(raza);
    }

    @Override
    public void setEdad(int edad) {
        mascota.setEdad(edad);
    }

    @Override
    public void setSexo(String sexo) {
        mascota.setSexo(sexo);
    }

    @Override
    public void setTarjetaVacunas(String vacunas) {
        mascota.setTarjetaVacunas(vacunas);
    }

    @Override
    public void setEstadoInicial(EstadoMascota estado) {
        mascota.setEstado(estado);
    }

    @Override
    public void setSize(String mediano) {
        mascota.setSize(mediano);
    }

    @Override
    public Mascota build() {
        Mascota productoFinal = this.mascota;
        this.reset();
        return productoFinal;
    }
}

package com.example.demo.builder;

import com.example.demo.model.Mascota;
import com.example.demo.state.EstadoAdoptada;
import com.example.demo.state.EstadoDisponible;

public class GatoBuilder implements MascotaBuilder {

    private Mascota mascota;

    public GatoBuilder() {
        this.reset();
    }

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
    public void setSize(String size) {
        mascota.setSize(size);
    }

    @Override
    public void setTarjetaVacunas(String vacunas) {
        mascota.setTarjetaVacunas(vacunas);
    }

    @Override
    public void setEstadoInicial(boolean estado) {
        mascota.setAdoptado(estado);

        if (estado) {
            mascota.setEstado(new EstadoAdoptada());
        } else {
            mascota.setEstado(new EstadoDisponible());
        }
    }

    @Override
    public void setImagenUrl(String imagenUrl) {
        mascota.setImagenUrl(imagenUrl);
    }

    @Override
    public Mascota build() {
        Mascota productoFinal = this.mascota;
        this.reset();
        return productoFinal;
    }
}
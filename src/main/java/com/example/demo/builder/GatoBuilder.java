package com.example.demo.builder;

import com.example.demo.model.Mascota;
import com.example.demo.state.EstadoMascota;

public class GatoBuilder implements MascotaBuilder {
    private Mascota mascota;

    public GatoBuilder() {
        this.reset();
    }

    @Override
    public void reset() {
        // Inicializa un nuevo objeto Mascota
        this.mascota = new Mascota();
    }

    // Los siguientes métodos simplemente pasan los valores al Producto
    @Override
    public void setNombre(String nombre) { mascota.setNombre(nombre); }

    @Override
    public void setRaza(String raza) { mascota.setRaza(raza); }

    @Override
    public void setEdad(int edad) { mascota.setEdad(edad); }

    @Override
    public void setSexo(String sexo) { mascota.setSexo(sexo); }

    @Override
    public void setTarjetaVacunas(String vacunas) {
        // Lógica específica del Gato (ejemplo: formato diferente)
        //mascota.setTarjetaVacunas("[Feline Vax] " + vacunas);
    }

    @Override
    public void setHistorial(String historial) { /*mascota.setHistorial(historial);*/ }

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
        // Lógica de Validación y/o Inicialización por Defecto

        Mascota productoFinal = this.mascota;
        this.reset();
        return productoFinal;
    }
}
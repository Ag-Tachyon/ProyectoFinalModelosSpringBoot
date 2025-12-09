package com.example.demo.model;

import com.example.demo.state.EstadoMascota;
import java.io.Serializable;

public class Mascota implements Serializable {

    private static final long serialVersionUID = 1L;

    // Atributos privados, solo modificables a través del Builder
    private String nombre;
    private String raza;
    private String size;
    private int edad;
    private String sexo;
    private String tarjetaVacunas;
    private EstadoMascota estado; // Estado debe ser Serializable también
    private String historialEventos;

    public Mascota() {}

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setRaza(String raza) { this.raza = raza; }
    public void setEdad(int edad) { this.edad = edad; }
    public void setSexo(String sexo) { this.sexo = sexo; }
    public void setSize(String size) { this.size = size; }
    public void setTarjetaVacunas(String tarjetaVacunas) { this.tarjetaVacunas = tarjetaVacunas; }
    public void setHistorialEventos(String historialEventos) { this.historialEventos = historialEventos; }

    public String getNombre() { return nombre; }
    public String getRaza() { return raza; }
    public int getEdad() { return edad; }
    public String getSize() { return size; }
    public String getSexo() { return sexo; }
    public String getTarjetaVacunas() { return tarjetaVacunas; }
    public EstadoMascota getEstado() { return estado; }
    public String getHistorialEventos() { return historialEventos; }

    public void setEstado(EstadoMascota estadoInicial) {
        this.estado = estadoInicial;
    }

    public void adoptarMascota() {
        this.estado.adoptarMascota(this);
    }

    public void devolverMascota() {
        this.estado.devolverMascota(this);
    }

    public String mostrarInfo() {
        return "Mascota [Nombre: " + nombre + ", Raza: " + raza + ", Edad: " + edad +
                ", Sexo: " + sexo + ", Vacunas: " + tarjetaVacunas + "]";
    }
}

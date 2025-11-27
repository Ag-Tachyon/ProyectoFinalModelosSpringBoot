package com.example.demo.model;

import com.example.demo.state.EstadoMascota;;

public class Mascota {
    // Atributos privados, solo modificables a través del Builder
    private String nombre;
    private String raza;
    private String size;
    private int edad;
    private String sexo;
    private String tarjetaVacunas;
    private EstadoMascota estado; // Referencia al estado actual
    private String historialEventos; // Simplificado de List<Evento>

    // Constructor privado para forzar el uso del Builder
    public Mascota() {}

    // Setters internos, el Builder los usa para ensamblar
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setRaza(String raza) { this.raza = raza; }
    public void setEdad(int edad) { this.edad = edad; }
    public void setSexo(String sexo) { this.sexo = sexo; }
    public void setSize(String size) { this.size = size; }
    public String getNombre() { return this.nombre; }
    public String getRaza() { return this.raza; }
    public int getEdad() { return this.edad; }
    public String getSexo() { return this.sexo; }
    public String getTarjetaVacunas() { return this.tarjetaVacunas; }
    public EstadoMascota getEstado() { return this.estado; }
    public String getHistorialEventos() { return this.historialEventos; }
    public void setTarjetaVacunas(String tarjetaVacunas) { this.tarjetaVacunas = tarjetaVacunas; }
    public void setHistorialEventos(String historialEventos) { this.historialEventos = historialEventos; }

    public void setEstado(EstadoMascota estadoInicial) {
        this.estado = estadoInicial;
    }

    public void adoptarMascota() {
        this.estado.adoptarMascota(this);
    }
    public void devolverMascota() {
        this.estado.devolverMascota(this);
    }

    // Método para mostrar la información del producto final
    public String mostrarInfo() {
        return "Mascota [Nombre: " + nombre + ", Raza: " + raza + ", Edad: " + edad +
                ", Sexo: " + sexo + ", Vacunas: " + tarjetaVacunas + "]";
    }
}

package com.example.demo.model;

import com.example.demo.state.EstadoMascota;
import java.io.Serializable;
import java.util.UUID;

public class Mascota implements Serializable {

    private static final long serialVersionUID = 1L;

    // --- NUEVO CAMPO ID ---
    private String id;

    // Atributos privados
    private String nombre;
    private String raza;
    private String size;
    private int edad;
    private String sexo;
    private String tarjetaVacunas;
    private EstadoMascota estado;
    private String historialEventos;

    public Mascota() {
        // Generar un ID único al crear la Mascota si no se asigna explícitamente
        // Esto solo es una solución temporal si el Builder no lo maneja.
        this.id = UUID.randomUUID().toString();
    }

    // --- GETTER Y SETTER para ID ---
    public String getId() { return id; }
    // Dejamos el setter privado o lo removemos si el ID debe ser inmutable después de la creación.
    public void setId(String id) { this.id = id; }

    // --- Otros Setters y Getters (iguales) ---

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

    // --- Métodos del Patrón State (iguales) ---

    public void setEstado(EstadoMascota estadoInicial) {
        this.estado = estadoInicial;
    }

    public void adoptarMascota(Mascota mascota) {
        // Llama al método del estado actual, pasándose a sí misma (el contexto)
        this.estado.adoptarMascota(this);
    }

    public void devolverMascota() {
        this.estado.devolverMascota(this);
    }

    public String mostrarInfo() {
        return "Mascota [ID: " + id + ", Nombre: " + nombre + ", Raza: " + raza + ", Edad: " + edad +
                ", Sexo: " + sexo + ", Vacunas: " + tarjetaVacunas + "]";
    }
}
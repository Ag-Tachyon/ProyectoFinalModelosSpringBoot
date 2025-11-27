package com.example.demo.model;

import java.time.LocalDate;

public class Evento {

    private String descripcion;
    private LocalDate fecha; // Usamos LocalDate para manejar fechas de forma segura

    // --- Constructor ---

    public Evento(String descripcion, LocalDate fecha) {
        this.descripcion = descripcion;
        this.fecha = fecha;
    }

    // --- Getters ---

    public String getDescripcion() {
        return descripcion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    // --- Setters (Opcionales, si se permite modificar el evento) ---

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    // --- Método toString (Para impresión fácil) ---

    @Override
    public String toString() {
        return "Evento [Descripción: " + descripcion + ", Fecha: " + fecha + "]";
    }
}
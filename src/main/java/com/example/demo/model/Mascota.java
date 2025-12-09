package com.example.demo.model;

import com.example.demo.state.EstadoMascota;
import com.example.demo.state.EstadoDisponible;
import java.io.Serializable;

public class Mascota implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nombre;
    private String raza;
    private int edad;
    private String sexo;
    private String size;
    private String tarjetaVacunas;
    private EstadoMascota estado;
    private boolean adoptado;
    private String imagenUrl; // Nuevo campo

    public Mascota() {
        this.estado = new EstadoDisponible();
        this.adoptado = false;
    }

    // Getters y Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getRaza() { return raza; }
    public void setRaza(String raza) { this.raza = raza; }

    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad; }

    public String getSexo() { return sexo; }
    public void setSexo(String sexo) { this.sexo = sexo; }

    public String getSize() { return size; }
    public void setSize(String size) { this.size = size; }

    public String getTarjetaVacunas() { return tarjetaVacunas; }
    public void setTarjetaVacunas(String tarjetaVacunas) { this.tarjetaVacunas = tarjetaVacunas; }

    public EstadoMascota getEstado() { return estado; }
    public void setEstado(EstadoMascota estado) {
        this.estado = estado;
        this.adoptado = estado.getNombreEstado().equals("Adoptada");
    }

    public boolean isAdoptado() { return adoptado; }
    public void setAdoptado(boolean adoptado) { this.adoptado = adoptado; }

    public String getImagenUrl() { return imagenUrl; }
    public void setImagenUrl(String imagenUrl) { this.imagenUrl = imagenUrl; }
}
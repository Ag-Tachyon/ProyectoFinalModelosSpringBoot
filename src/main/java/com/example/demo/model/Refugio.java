package com.example.demo.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "tipo"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = RefugioGatos.class, name = "gatos"),
        @JsonSubTypes.Type(value = RefugioPerros.class, name = "perros")
})

public abstract class Refugio implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nombre;
    private String direccion;
    private int capacidad;

    private List<Mascota> mascotas;
    private List<String> seguidores;

    public Refugio(String nombre, String direccion, int capacidad) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.capacidad = capacidad;
        this.mascotas = new ArrayList<>();
        this.seguidores = new ArrayList<>();
    }

    public Refugio() {}

    public abstract String mostrarInfoRefugio();

    public abstract void registrarMascota(Mascota mascota);

    // GETTERS Y SETTERS
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public int getCapacidad() { return capacidad; }
    public void setCapacidad(int capacidad) { this.capacidad = capacidad; }

    public List<Mascota> getMascotas() { return mascotas; }
    public void setMascotas(List<Mascota> mascotas) { this.mascotas = mascotas; }

    public List<String> getSeguidores() { return seguidores; }
    public void setSeguidores(List<String> seguidores) { this.seguidores = seguidores; }
}

package com.example.demo.builder;

//import com.example.demo.model.Evento;
import com.example.demo.state.EstadoMascota; // Asumiendo que has creado el paquete state
import java.util.List;

public class Director {
    private MascotaBuilder builder;

    // Constructor que recibe un Builder concreto
    public Director(MascotaBuilder builder) {
        this.builder = builder;
    }

    // Método para cambiar el Builder en tiempo de ejecución
    public void changeBuilder(MascotaBuilder newBuilder) {
        this.builder = newBuilder;
    }

    // 1. Método para construir una mascota con datos mínimos de identificación.
    public void construirMascotaSimple(String nombre, int edad) {
        builder.reset();
        builder.setNombre(nombre);
        builder.setEdad(edad);
        builder.setSexo("Desconocido");
        builder.setSize("Mediano"); // Establece un valor por defecto
        // El Builder Concreto (Gato/Perro) se encargará de asignar el EstadoInicial
    }

    // 2. Método para construir una mascota lista para adopción (incluye estado inicial).
    public void construirMascotaParaAdopcion(String nombre, String raza, int edad, String size, EstadoMascota estadoInicial) {
        builder.reset();
        builder.setNombre(nombre);
        builder.setRaza(raza);
        builder.setEdad(edad);
        builder.setSize(size);
        builder.setSexo("Desconocido");
        builder.setEstadoInicial(estadoInicial);
        // La tarjeta de vacunas y el historial quedan pendientes
    }

    // 3. Método para construir una mascota compleja con historial completo (revisado el tipo de historial).
    // Nota: El tipo del historial se asume List<Evento> basado en el diagrama.
    public void construirMascotaCompleta(
            String nombre,
            String raza,
            int edad,
            String sexo,
            String size,
            String vacunas,
            //List<Evento> historial,
            EstadoMascota estadoInicial) {

        builder.reset();
        builder.setNombre(nombre);
        builder.setRaza(raza);
        builder.setEdad(edad);
        builder.setSexo(sexo);
        builder.setSize(size);
        builder.setTarjetaVacunas(vacunas);
        //builder.setHistorial(historial); // Usando List<Evento>
        builder.setEstadoInicial(estadoInicial);
    }
}
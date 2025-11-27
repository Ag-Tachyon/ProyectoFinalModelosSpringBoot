package com.example.demo.singleton;

import java.util.List;

// Interfaz DataManager del diagrama
public interface DataManager<T> {
    // T representa el tipo de dato que se leerá/guardará (Usuario, Mascota, Evento)

    // Método para leer datos (retorna una lista del tipo T)
    List<T> leerDatos();

    // Método para guardar datos (recibe una lista del tipo T)
    void guardarDatos(List<T> datos);
}
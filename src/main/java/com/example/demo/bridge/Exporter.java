package com.example.demo.bridge;

public interface Exporter {
    // El método de implementación de bajo nivel (PDF, HTML, etc.)
    void exportar(String contenido);
}
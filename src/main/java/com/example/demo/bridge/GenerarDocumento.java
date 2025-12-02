package com.example.demo.bridge;

import com.example.demo.bridge.Exporter;

public abstract class GenerarDocumento {

    protected String titulo;
    protected String contenido;
    protected Exporter exporter;

    public GenerarDocumento(Exporter exporter) {
        this.exporter = exporter;
    }

    public void generar() {
        exporter.exportar(contenido);
    }

    public void mostrar() {
        System.out.println("Título: " + titulo);
        System.out.println("Contenido:\n" + contenido);
    }

    // Los hijos deben llenar el contenido
    protected abstract void construirContenido();
}

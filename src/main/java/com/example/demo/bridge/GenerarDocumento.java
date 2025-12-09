package com.example.demo.bridge;

public abstract class GenerarDocumento {

    protected Exporter exporter;

    public GenerarDocumento(Exporter exporter) {
        this.exporter = exporter;
    }

    // contenido específico de cada documento
    public abstract String buildContent();

    // método común para generar el archivo
    public byte[] generate() throws Exception {
        return exporter.export(buildContent());
    }
}

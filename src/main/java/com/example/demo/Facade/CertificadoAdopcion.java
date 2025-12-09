package com.example.demo.Facade;

import com.example.demo.bridge.*;

public class CertificadoAdopcion extends GenerarDocumento {

    private final String nombre;
    private final String adoptante;

    public CertificadoAdopcion(Exporter exporter,
                                       String nombre,
                                       String adoptante) {

        super(exporter);
        this.nombre = nombre;
        this.adoptante = adoptante;
    }

    @Override
    public String buildContent() {
        return """
                Certificado de Adopción
                Mascota: %s
                Adoptante: %s

                ¡Gracias por adoptar!
                """.formatted(nombre, adoptante);
    }
}

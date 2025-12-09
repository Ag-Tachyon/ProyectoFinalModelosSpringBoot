package com.example.demo.Facade;

import com.example.demo.bridge.*;

public class FichaMedica extends GenerarDocumento {

    private final String nombreMascota;
    private final String raza;

    public FichaMedica(Exporter exporter,
                               String nombreMascota,
                               String raza) {

        super(exporter);
        this.nombreMascota = nombreMascota;
        this.raza = raza;
    }

    @Override
    public String buildContent() {
        return """
                Ficha Médica
                Mascota: %s
                Raza: %s
                Estado: %s
                """.formatted(nombreMascota, raza);
    }
}

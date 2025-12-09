package com.example.demo.Facade;

import com.example.demo.bridge.*;

public class CertificadoAdopcion extends GenerarDocumento {

    private final String nombreMascota;
    private final String adoptante;

    public CertificadoAdopcion(Exporter exporter,
                               String nombreMascota,
                               String adoptante) {
        super(exporter);
        this.nombreMascota = nombreMascota;
        this.adoptante = adoptante;
    }

    @Override
    public String buildContent() {
        return String.format(
                "============================================\n" +
                        "CERTIFICADO DE ADOPCION\n" +
                        "============================================\n\n" +
                        "MASCOTA ADOPTADA: %s\n" +
                        "ADOPTANTE: %s\n" +
                        "FECHA: %s\n\n" +
                        "----------------------------------------------------\n" +
                        "DECLARACION DE ADOPCION\n" +
                        "----------------------------------------------------\n\n" +
                        "Por medio del presente, se certifica que:\n\n" +
                        "1. %s ha adoptado formalmente a %s.\n" +
                        "2. El adoptante se compromete a:\n" +
                        "   - Proporcionar cuidado y atencion veterinaria\n" +
                        "   - Ofrecer un hogar seguro y amoroso\n" +
                        "   - Responsabilizarse de su bienestar\n\n" +
                        "3. La mascota cuenta con:\n" +
                        "   [X] Cartilla de vacunacion al dia\n" +
                        "   [X] Desparasitacion completa\n" +
                        "   [X] Examen medico general\n\n" +
                        "============================================\n" +
                        "FELICITACIONES POR SU NUEVO COMPAÑERO!\n" +
                        "============================================\n",
                nombreMascota,
                adoptante,
                new java.text.SimpleDateFormat("dd/MM/yyyy").format(new java.util.Date()),
                adoptante,
                nombreMascota);
    }
}
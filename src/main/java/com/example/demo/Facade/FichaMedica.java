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
        return String.format("""
                ============================================
                FICHA MEDICA VETERINARIA
                ============================================
                
                DATOS DE LA MASCOTA:
                --------------------
                Nombre: %s
                Raza: %s
                Fecha de emision: %s
                
                HISTORIAL MEDICO:
                -----------------
                - Vacunacion: Completa
                - Desparasitacion: Al dia
                - Estado de salud: Optimo
                - Observaciones: Listo para adopcion
                
                RECOMENDACIONES:
                ---------------
                1. Visita veterinaria cada 6 meses
                2. Alimentacion balanceada
                3. Ejercicio diario
                4. Control de peso periodico
                
                VACUNAS APLICADAS:
                ------------------
                [X] Vacuna multiple (DHPP)
                [X] Antirrabica
                [X] Leishmaniosis
                
                FIRMAS:
                -------
                
                _________________________
                Veterinario Responsable
                
                _________________________
                Responsable del Refugio
                
                ============================================
                """,
                nombreMascota,
                raza,
                new java.text.SimpleDateFormat("dd/MM/yyyy").format(new java.util.Date()));
    }
}
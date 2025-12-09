package com.example.demo.Facade;

import com.example.demo.bridge.*;
import com.example.demo.model.Usuario;
import com.example.demo.model.Mascota;

import java.util.HashMap;
import java.util.Map;

public class Adopcion {

    public Map<String, byte[]> generarDocumentos(Mascota mascota, Usuario usuario,
                                                 Exporter exporter) throws Exception {

        if (mascota == null) {
            throw new IllegalArgumentException("Mascota no puede ser null");
        }
        if (usuario == null) {
            throw new IllegalArgumentException("Usuario no puede ser null");
        }
        if (exporter == null) {
            throw new IllegalArgumentException("Exporter no puede ser null");
        }

        Map<String, byte[]> documentos = new HashMap<>();

        try {
            // Certificado
            System.out.println("Generando certificado para: " + mascota.getNombre());
            GenerarDocumento certificado = new CertificadoAdopcion(
                    exporter,
                    mascota.getNombre(),
                    usuario.getNombreUsuario()
            );

            byte[] archivoCertificado = certificado.generate();
            documentos.put("certificado", archivoCertificado);
            System.out.println("✅ Certificado generado");

            // Ficha médica
            System.out.println("Generando ficha médica para: " + mascota.getNombre());
            GenerarDocumento ficha = new FichaMedica(
                    exporter,
                    mascota.getNombre(),
                    mascota.getRaza() != null ? mascota.getRaza() : "No especificada"
            );

            byte[] archivoFicha = ficha.generate();
            documentos.put("ficha", archivoFicha);
            System.out.println("✅ Ficha médica generada");

        } catch (Exception e) {
            System.err.println("❌ Error generando documentos: " + e.getMessage());
            e.printStackTrace();
            throw new Exception("Error al generar documentos: " + e.getMessage(), e);
        }

        return documentos;
    }
}
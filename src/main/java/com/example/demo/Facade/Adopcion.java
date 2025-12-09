package com.example.demo.Facade;

import com.example.demo.bridge.*;
import com.example.demo.model.Usuario;
import com.example.demo.model.Mascota;

import java.util.HashMap;
import java.util.Map;

public class Adopcion {

    public Map<String, byte[]> generarDocumentos(Mascota mascota, Usuario usuario,
                                                 Exporter exporter) throws Exception {

        Map<String, byte[]> documentos = new HashMap<>();

        // Certificado
        GenerarDocumento certificado = new CertificadoAdopcion(
                exporter,
                mascota.getNombre(),
                usuario.getNombreUsuario()
        );

        byte[] archivoCertificado = certificado.generate();
        documentos.put("certificado", archivoCertificado);

        // Ficha médica
        GenerarDocumento ficha = new FichaMedica(
                exporter,
                mascota.getNombre(),
                mascota.getRaza()
        );

        byte[] archivoFicha = ficha.generate();
        documentos.put("ficha", archivoFicha);

        return documentos;
    }
}

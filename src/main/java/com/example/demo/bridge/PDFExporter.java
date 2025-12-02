package com.example.demo.bridge;

public class PDFExporter implements Exporter {
    @Override
    public void exportar(String contenido) {
        // Lógica para generar un PDF (usando alguna librería como iText o Apache PDFBox)
        System.out.println("--- EXPORTANDO A PDF ---");
        System.out.println("Generando binario PDF para el contenido: " + contenido.substring(0, Math.min(contenido.length(), 40)) + "...");
        System.out.println("Documento PDF generado exitosamente.");
    }
}
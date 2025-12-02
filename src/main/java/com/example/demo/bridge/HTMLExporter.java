package com.example.demo.bridge;

public class HTMLExporter implements Exporter {
    @Override
    public void exportar(String contenido) {
        // Lógica para dar formato HTML al contenido
        System.out.println("--- EXPORTANDO A HTML ---");
        System.out.println("<html><body>");
        System.out.println(contenido);
        System.out.println("</body></html>");
        System.out.println("Documento HTML generado exitosamente.");
    }
}
package com.example.demo.bridge;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;

import java.io.ByteArrayOutputStream;

public class PDFExporter implements Exporter {

    @Override
    public byte[] export(String content) throws Exception {
        try (PDDocument document = new PDDocument();
             ByteArrayOutputStream baos = new ByteArrayOutputStream()) {

            PDPage page = new PDPage();
            document.addPage(page);

            try (PDPageContentStream stream = new PDPageContentStream(document, page)) {
                escribirContenidoSimple(stream, content, page);
            }

            document.save(baos);
            return baos.toByteArray();
        }
    }

    private void escribirContenidoSimple(PDPageContentStream stream, String content, PDPage page) throws Exception {
        PDFont font = new PDType1Font(Standard14Fonts.FontName.HELVETICA);
        PDFont fontBold = new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD);

        float margin = 50;
        float yStart = page.getMediaBox().getHeight() - margin;
        float yPosition = yStart;
        float leading = 14.5f;

        stream.beginText();
        stream.setFont(font, 12);
        stream.newLineAtOffset(margin, yPosition);

        // Reemplazar caracteres no compatibles
        String contenidoLimpio = limpiarCaracteresNoCompatibles(content);
        String[] lines = contenidoLimpio.split("\n");

        for (String line : lines) {
            // Cambiar fuente para títulos
            if (line.contains("CERTIFICADO") || line.contains("FICHA MEDICA")) {
                stream.setFont(fontBold, 14);
            } else if (line.contains("DECLARACION") || line.contains("HISTORIAL") ||
                    line.contains("RECOMENDACIONES") || line.contains("VACUNAS") ||
                    line.contains("FIRMAS")) {
                stream.setFont(fontBold, 12);
            } else {
                stream.setFont(font, 12);
            }

            // Escribir línea
            if (!line.trim().isEmpty()) {
                stream.showText(line);
                yPosition -= leading;

                // Nueva línea
                stream.newLineAtOffset(0, -leading);

                // Si se acaba el espacio, terminar
                if (yPosition < margin) {
                    stream.showText("... (documento continua)");
                    break;
                }
            }
        }

        stream.endText();
    }

    private String limpiarCaracteresNoCompatibles(String content) {
        // Reemplazar caracteres especiales por equivalentes ASCII
        return content
                .replace("✓", "[X]")  // Checkmark por [X]
                .replace("✅", "[OK]") // Emoji check por [OK]
                .replace("–", "-")     // Dash largo por guión normal
                .replace("—", "-")     // Em dash por guión normal
                .replace("“", "\"")    // Comillas izquierdas
                .replace("”", "\"")    // Comillas derechas
                .replace("‘", "'")     // Apóstrofe izquierdo
                .replace("’", "'")     // Apóstrofe derecho
                .replace("…", "...")   // Elipsis
                .replace("¡", "!")     // Exclamación invertida
                .replace("¿", "?");    // Interrogación invertida
    }
}
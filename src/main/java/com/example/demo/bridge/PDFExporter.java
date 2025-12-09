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

                // Crear la fuente (PDFBox 3.x)
                PDFont font = new PDType1Font(Standard14Fonts.FontName.HELVETICA);
                stream.setFont(font, 12f);

                stream.beginText();
                stream.newLineAtOffset(50, 750);

                // Mostrar cada línea (evita overflow simple, no contempla saltos de página automáticos)
                for (String line : content.split("\n")) {
                    stream.showText(line);
                    stream.newLineAtOffset(0, -15);
                }

                stream.endText();
            }

            document.save(baos);
            return baos.toByteArray();
        }
    }
}

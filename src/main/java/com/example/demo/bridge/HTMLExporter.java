package com.example.demo.bridge;

import java.nio.charset.StandardCharsets;

public class HTMLExporter implements Exporter {

    @Override
    public byte[] export(String content) {
        String html = """
                <html>
                    <head>
                        <meta charset="UTF-8"/>
                        <title>Documento</title>
                    </head>
                    <body>
                       %s
                    </body>
                </html>
                """.formatted(content);

        return html.getBytes(StandardCharsets.UTF_8);
    }
}

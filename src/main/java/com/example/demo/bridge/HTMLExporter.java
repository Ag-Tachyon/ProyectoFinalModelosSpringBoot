package com.example.demo.bridge;

import java.nio.charset.StandardCharsets;

public class HTMLExporter implements Exporter {

    @Override
    public byte[] export(String content) {
        // Convertir saltos de línea a <br> para HTML
        String htmlContent = content.replace("\n", "<br>");

        String html = String.format("""
            <!DOCTYPE html>
            <html>
                <head>
                    <meta charset="UTF-8"/>
                    <title>Documento de Adopción</title>
                    <style>
                        body {
                            font-family: Arial, sans-serif;
                            margin: 40px;
                            line-height: 1.6;
                        }
                        .header {
                            text-align: center;
                            border-bottom: 2px solid #333;
                            padding-bottom: 20px;
                            margin-bottom: 30px;
                        }
                        .section {
                            margin-bottom: 20px;
                        }
                        .section-title {
                            font-weight: bold;
                            color: #2c3e50;
                            border-bottom: 1px solid #ddd;
                            padding-bottom: 5px;
                            margin-bottom: 10px;
                        }
                        .signature {
                            margin-top: 50px;
                            text-align: center;
                        }
                        .signature-line {
                            border-top: 1px solid #000;
                            width: 300px;
                            display: inline-block;
                            margin-top: 60px;
                        }
                        .footer {
                            margin-top: 50px;
                            text-align: center;
                            font-size: 12px;
                            color: #666;
                        }
                    </style>
                </head>
                <body>
                    %s
                </body>
            </html>
            """, htmlContent);

        return html.getBytes(StandardCharsets.UTF_8);
    }
}
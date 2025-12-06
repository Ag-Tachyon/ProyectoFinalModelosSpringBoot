package com.example.demo.Decorator;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class NotificacionCorreo extends BaseNotifier {
    private final JavaMailSender mailSender;
    private final String emailRemitente;

    public NotificacionCorreo(JavaMailSender mailSender,
                              @Value("${spring.mail.username:juancanon25@gmail.com}") String emailRemitente) {
        super(null);
        this.mailSender = mailSender;
        this.emailRemitente = emailRemitente;
    }

    @Override
    public void enviarMensaje(String mensaje, String destinatario) {
        enviarCorreo(mensaje, destinatario);
        // No llamamos a super porque no hay wrappe
    }

    private void enviarCorreo(String mensaje, String destinatario) {
        try {
            SimpleMailMessage email = new SimpleMailMessage();
            email.setTo(destinatario);
            email.setSubject("Notificación del Sistema");
            email.setText(mensaje);
            email.setFrom(emailRemitente);

            mailSender.send(email);
            System.out.println("✅ Correo enviado a: " + destinatario);

        } catch (Exception e) {
            System.err.println("❌ Error enviando correo: " + e.getMessage());
        }
    }
}
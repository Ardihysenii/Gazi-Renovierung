package com.gazirenovierung.gazirenovierung.services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendContactEmail(String name, String email, String message) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

            // Professional HTML with GAZI RENOVIERUNG branding
            String htmlContent =
                    "<div style='font-family: Arial, sans-serif; background-color: #f8f8f8; padding: 20px;'>" +
                            "<div style='max-width: 600px; margin: 0 auto; background: #ffffff; border: 1px solid #ddd; border-top: 5px solid #b8860b;'>" +
                            // Header
                            "<div style='padding: 20px; text-align: center; background: #1a1a1a;'>" +
                            "<h1 style='color: #ffffff; margin: 0; font-size: 22px; letter-spacing: 3px;'>GAZI RENOVIERUNG</h1>" +
                            "</div>" +
                            // Body
                            "<div style='padding: 30px;'>" +
                            "<h3 style='color: #1a1a1a; border-bottom: 1px solid #eee; padding-bottom: 10px;'>New Client Inquiry</h3>" +
                            "<p style='margin: 10px 0;'><strong>Name:</strong> " + name + "</p>" +
                            "<p style='margin: 10px 0;'><strong>Email:</strong> <a href='mailto:" + email + "' style='color: #b8860b;'>" + email + "</a></p>" +
                            "<div style='margin-top: 20px; padding: 15px; background: #fafafa; border-left: 3px solid #b8860b;'>" +
                            "<strong>Message:</strong><br><br>" + message.replace("\n", "<br>") +
                            "</div>" +
                            "</div>" +
                            // Footer
                            "<div style='padding: 15px; text-align: center; background: #f1f1f1; font-size: 11px; color: #777;'>" +
                            "This inquiry was generated from the GAZI RENOVIERUNG contact form." +
                            "</div>" +
                            "</div>" +
                            "</div>";

            helper.setFrom("Ardihyseni45@gmail.com"); // MUST match your spring.mail.username
            helper.setTo("Ardihyseni988@gmail.com");    // ALWAYS goes to your Gmail
            helper.setSubject("New Lead: " + name);
            helper.setText(htmlContent, true);
            helper.setReplyTo(email); // Click 'Reply' in Gmail to respond directly to the client

            mailSender.send(mimeMessage);

        } catch (MessagingException e) {
            e.printStackTrace(); // Check your IDE console if it fails!
        }
    }
}
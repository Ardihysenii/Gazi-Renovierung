package com.gazirenovierung.gazirenovierung.services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import java.io.UnsupportedEncodingException;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String senderEmail;

    // Kemi shtuar throws që Controller-i ta marrë vesh kur dështon Render-i
    public void sendContactEmail(String name, String email, String message) throws MessagingException, UnsupportedEncodingException {

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

        // DIZAJNI JOT I PAPREKUR
        String htmlContent =
                "<div style='background-color: #ffffff; padding: 50px 20px; font-family: \"Helvetica Neue\", Helvetica, Arial, sans-serif; color: #1a1a1a;'>" +
                        "<div style='max-width: 550px; margin: 0 auto; border: 1px solid #e0e0e0; border-radius: 2px;'>" +
                        "<div style='padding: 40px 20px; text-align: center; background-color: #1a1a1a;'>" +
                        "<h1 style='color: #ffffff; margin: 0; font-size: 20px; letter-spacing: 5px; font-weight: 300; text-transform: uppercase;'>GAZI RENOVIERUNG</h1>" +
                        "</div>" +
                        "<div style='padding: 50px 40px;'>" +
                        "<p style='font-size: 13px; color: #b8860b; text-transform: uppercase; letter-spacing: 2px; margin-bottom: 10px; font-weight: bold;'>New Inquiry</p>" +
                        "<h2 style='font-size: 24px; margin: 0 0 30px 0; font-weight: 300; color: #1a1a1a;'>Client Message</h2>" +
                        "<div style='margin-bottom: 40px; font-size: 15px; line-height: 1.8; color: #444;'>" +
                        "<div style='margin-bottom: 15px; border-bottom: 1px solid #f0f0f0; padding-bottom: 10px;'>" +
                        "<span style='color: #999; width: 80px; display: inline-block; font-size: 12px; text-transform: uppercase;'>From</span>" +
                        "<strong style='color: #1a1a1a;'>" + name + "</strong>" +
                        "</div>" +
                        "<div style='margin-bottom: 15px; border-bottom: 1px solid #f0f0f0; padding-bottom: 10px;'>" +
                        "<span style='color: #999; width: 80px; display: inline-block; font-size: 12px; text-transform: uppercase;'>Email</span>" +
                        "<a href='mailto:" + email + "' style='color: #1a1a1a; text-decoration: none; border-bottom: 1px solid #b8860b;'>" + email + "</a>" +
                        "</div>" +
                        "<div style='margin-top: 30px;'>" +
                        "<span style='color: #999; font-size: 12px; text-transform: uppercase; display: block; margin-bottom: 10px;'>Message</span>" +
                        "<div style='color: #1a1a1a; white-space: pre-wrap;'>" + message + "</div>" +
                        "</div>" +
                        "</div>" +
                        "<div style='text-align: center; margin-top: 50px;'>" +
                        "<a href='mailto:" + email + "' style='background-color: #1a1a1a; color: #ffffff; padding: 15px 35px; text-decoration: none; font-size: 12px; letter-spacing: 2px; text-transform: uppercase; border-radius: 0px;'>Reply to Client</a>" +
                        "</div>" +
                        "</div>" +
                        "<div style='padding: 30px; background-color: #fafafa; text-align: center; border-top: 1px solid #f0f0f0;'>" +
                        "<p style='font-size: 10px; color: #aaa; letter-spacing: 1px; margin: 0;'>GAZI RENOVIERUNG &bull; 2026 OFFICIAL SYSTEM</p>" +
                        "</div>" +
                        "</div>" +
                        "</div>";

        helper.setFrom(senderEmail, "Gazi Renovierung");
        helper.setTo(senderEmail);
        helper.setSubject("New Project Inquiry - " + name);
        helper.setText(htmlContent, true);
        helper.setReplyTo(email);

        mailSender.send(mimeMessage);
        System.out.println("SUCCESS: Email sent to " + senderEmail);
    }
}
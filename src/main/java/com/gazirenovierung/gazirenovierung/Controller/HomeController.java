package com.gazirenovierung.gazirenovierung.Controller;

import com.gazirenovierung.gazirenovierung.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class HomeController {

    @Autowired
    private EmailService emailService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/portfolio")
    public String portfolio() {
        return "portfolio";
    }

    @GetMapping("/aboutus")
    public String aboutUs() {
        return "aboutus";
    }

    /**
     * E KORRIGJUAR PËR JAVASCRIPT FETCH DHE RENDER
     */
    @PostMapping("/contact")
    @ResponseBody // Kjo bën që të kthehet tekst/JSON, jo faqe HTML
    public ResponseEntity<?> handleContact(@RequestParam String name,
                                           @RequestParam String email,
                                           @RequestParam String message) {

        System.out.println("--- KËRKESË E RE NË RENDER ---");

        try {
            // Thirrja e Service-it (që tani i "hedh" gabimet lart)
            emailService.sendContactEmail(name, email, message);

            System.out.println("SUCCESS: Email u dërgua!");
            return ResponseEntity.ok().body(Map.of("status", "success", "message", "Sent successfully!"));

        } catch (Exception e) {
            System.err.println("CRITICAL ERROR TE RENDER: " + e.getMessage());
            e.printStackTrace();

            // I dërgojmë JavaScript-it statusin 500 dhe mesazhin e gabimit
            return ResponseEntity.status(500).body(Map.of("status", "error", "message", e.getMessage()));
        }
    }
}
package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // Indica que esta clase maneja peticiones web
public class HomeController {

    @GetMapping({ "/index"})
    public String index() {
        // Spring Boot buscará 'index.html' en src/main/resources/templates/
        return "index";
    }
}

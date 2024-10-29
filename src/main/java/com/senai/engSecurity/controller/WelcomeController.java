package com.senai.engSecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class WelcomeController {

    @GetMapping
    public String showWelcomeMessage() {
        return "Bem-vindo ao Sistema!";
    }

    @GetMapping("/user/dashboard")
    public String userDashboard() {
        return "Área de Usuário Autorizada!";
    }

    @GetMapping("/admin/console")
    public String adminConsole() {
        return "Área Administrativa Exclusiva!";
    }
}

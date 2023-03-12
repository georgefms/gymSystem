package com.georgefms.gymsystem.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessagesController {

    @GetMapping
    public String welcome(){
        return "Bem vindo ao sistema";
    }

    @GetMapping("/users")
    public String users(){
        return "Usuario com permissão";
    }

    @GetMapping("/admin")
    public String admin(){
        return "Somente o ADMIN";
    }

    @GetMapping("/any")
    public String any(){
        return "Somente o usuário";
    }
}

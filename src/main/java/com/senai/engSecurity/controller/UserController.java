package com.senai.engSecurity.controller;

import com.senai.engSecurity.model.User;
import com.senai.engSecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/register")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public User registerUser(@RequestBody User user) {
        User registeredUser = userService.save(user);
        System.out.println("Usuário registrado com sucesso!");
        return registeredUser;
    }
}

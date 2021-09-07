package com.example.demo.controllers;

import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService service;


    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/user")
    public String showuser(ModelMap model, Principal principal){
        model.addAttribute("user", service.getUserByUsername(principal.getName()));
        model.addAttribute("roles", service.getStringRolesByUsername(principal.getName()));
        return "users/show";
    }



}

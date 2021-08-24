package com.smh.myprojectone.controller;


import com.smh.myprojectone.repository.RoleRepository;
import com.smh.myprojectone.repository.UserRepository;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Data
public class LoginController {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("login")
    private String login(){
        return "login";
    }

    @GetMapping("register")
    public String register(){
        return "register";
    }

}

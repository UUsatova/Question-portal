package com.softarex.QuestionsPortal.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/login")
public class AuthController {

    @GetMapping
    public String login() {
        return "login-page";
    }

    @GetMapping("/ok")
    public String home() {
        return "redirect:/user/show";
    }

}

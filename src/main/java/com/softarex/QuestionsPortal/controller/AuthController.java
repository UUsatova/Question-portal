package com.softarex.QuestionsPortal.controller;


import com.softarex.QuestionsPortal.dto.EmailTemplate;
import com.softarex.QuestionsPortal.entity.User;
import com.softarex.QuestionsPortal.service.EmailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/login")
public class AuthController {

    private final EmailService emailService;


    @GetMapping
    public String login() throws MessagingException {
        return "login-page";
    }

    @GetMapping("/ok")
    public String home() {
        return "redirect:/user/show";
    }

}

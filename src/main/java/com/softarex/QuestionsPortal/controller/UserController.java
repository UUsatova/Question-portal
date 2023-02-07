package com.softarex.QuestionsPortal.controller;

import com.softarex.QuestionsPortal.dto.UserDtoWithPassword;
import com.softarex.QuestionsPortal.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequiredArgsConstructor
@RequestMapping(("/user"))
public class UserController {
    private final UserService userService;

    @GetMapping("registration")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserDtoWithPassword());
        return "registration-page";
    }

    @PostMapping("registration")
    public String registerUserAccount(@ModelAttribute("user") /*@Validated(Creation.class)*/ UserDtoWithPassword registrationDto) {
        userService.addUser(registrationDto);
        return  "redirect:/login-page?success"; //+на логин редирект + позволения расставить
    }

    @GetMapping("/show")
    public String showUserDataForm(Model model) {
        model.addAttribute("user", userService.getUserDtoOfAuthenticatedUser());
        return "userData-page";
    }

    @GetMapping("/delete")
    public String showDeleteUserForm(Model model) {
        model.addAttribute("password", new String());
        return "delete-page";
    }

    @PostMapping("/delete")
    public String deleteUser(@ModelAttribute("password") String password) {
        if (userService.deleteUser(password)) return "redirect:/user/registration";
        else return "redirect:/user/delete?error";
    }

    @GetMapping("/update")
    public String showUpdateUserForm(Model model) {
        model.addAttribute("user",userService.getUserDtoWithPasswordOfAuthenticatedUser());
        return "update-page";
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute("user") /*@Validated(Creation.class)*/UserDtoWithPassword updateUserDto) {
        userService.updateUser(updateUserDto); //либо вынести апдейт пароля отдельно либо убрать принудитьельное заполнение
        return "redirect:/user/show";
    }

}

//+формы
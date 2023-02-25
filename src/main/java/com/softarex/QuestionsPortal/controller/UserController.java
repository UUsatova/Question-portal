package com.softarex.QuestionsPortal.controller;

import com.softarex.QuestionsPortal.dto.EmailTemplate;
import com.softarex.QuestionsPortal.dto.UserDtoWithPassword;
import com.softarex.QuestionsPortal.entity.User;
import com.softarex.QuestionsPortal.exception.IncorrectPasswordException;
import com.softarex.QuestionsPortal.group.Creation;
import com.softarex.QuestionsPortal.group.Update;
import com.softarex.QuestionsPortal.service.EmailService;
import com.softarex.QuestionsPortal.service.QuestionService;
import com.softarex.QuestionsPortal.service.UserService;
import jakarta.mail.MessagingException;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequiredArgsConstructor
@RequestMapping(("/user"))
public class UserController {
    private final UserService userService;
    private final QuestionService questionService;
    private final EmailService emailService;
    private final String CREATION="create";
    private final String DELETE="delete";

    @GetMapping("registration")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserDtoWithPassword());
        return "registration-page";
    }

    //ситуация когда в базе есть неактивный юзер с таким же имейлом
    @PostMapping("registration")
    public String registerUserAccount(@ModelAttribute("user") @Validated(Creation.class) UserDtoWithPassword registrationDto, BindingResult result, Model model) throws MessagingException {
        if (result.hasErrors() || !registrationDto.getPassword().equals(registrationDto.getHelperPassword()) ){
            result.addError(new FieldError("user", "helperPassword", "Passwords dont match"));
            return "registration-page";
        }
        User createdUser = userService.addUser(registrationDto);
        EmailTemplate email = emailService.getUserCreateEmailTemplate(createdUser,CREATION);
        emailService.sendMail(email);
        return "redirect:/login?success";
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
    public String deleteUser(@ModelAttribute("password") String password) throws MessagingException {
        User deletedUser = userService.getAuthenticatedUser();
        EmailTemplate email = emailService.getUserCreateEmailTemplate(deletedUser,DELETE);
        emailService.sendMail(email);
        if (userService.softDeleteUser(password)) {
            questionService.softDeleteAllUserQuestions(deletedUser.getId());
            return "redirect:/user/registration";

        }
        else return "redirect:/user/delete?error";
    }

    @GetMapping("/update")
    public String showUpdateUserForm(Model model) {
        model.addAttribute("user", userService.getUserDtoWithPasswordOfAuthenticatedUser());
        return "update-page";
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute("user") @Validated(Update.class) UserDtoWithPassword updateUserDto, BindingResult result, Model model) throws IncorrectPasswordException {
        String authUserEmail = userService.getAuthenticatedUser().getEmail();
        User authUser = userService.getUserByEmail(authUserEmail);
        boolean isPasswordWillBeChanged = !updateUserDto.getHelperPassword().equals("");
        boolean isPasswordWrong =  isPasswordWillBeChanged && !authUser.getPassword().equals(updateUserDto.getPassword())  ;
        boolean isEmailChanged = !authUserEmail.equals(updateUserDto.getEmail());
        boolean isChangedEmailAlreadyExist = isEmailChanged && userService.getUserByEmail(updateUserDto.getEmail())!=null;

        if (result.hasErrors() || isPasswordWrong || isChangedEmailAlreadyExist) {
            if (isChangedEmailAlreadyExist) {
                result.addError(new FieldError("user", "email", "User with such email already exists"));
            }
            if (isPasswordWrong) {
                result.addError(new FieldError("user", "password", "Wrong password"));
            }
            return "update-page";
        }
        User user = userService.updateUser(updateUserDto); //разлогинить после смены email
        if (isEmailChanged) return "redirect:/login"; //высылалать письмо + сообщение на фронт
        return "redirect:/user/show";
    }

}
//Выглядит как много много логики в контроллере но по идее эти все разветвления необходимы
//для корректного отображения формы,а значит должны относится к слою контроллера
//изначально я думала эти все ошибки делать через хэндлер,но потом поняла что конкретно сетать на форму
//таким образом гораздо более адекватно чем через хэндлер

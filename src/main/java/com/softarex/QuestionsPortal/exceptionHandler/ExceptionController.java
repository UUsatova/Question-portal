package com.softarex.QuestionsPortal.exceptionHandler;

import com.softarex.QuestionsPortal.controller.UserController;
import com.softarex.QuestionsPortal.exception.IncorrectPasswordException;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionController {
    //надо подумать нужен ли он мне вообще

    // For UI Pages
    //@ExceptionHandler(IncorrectPasswordException.class)
//    public String getEditForm(IncorrectPasswordException ex) {
//        return "error";
//    }
}

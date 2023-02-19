package com.softarex.QuestionsPortal.validation;

import com.softarex.QuestionsPortal.repository.UserRepository;
import com.softarex.QuestionsPortal.validation.annotation.ExistAndActive;
import com.softarex.QuestionsPortal.validation.annotation.Unique;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@RequiredArgsConstructor
public class ExistEmailValidator implements
        ConstraintValidator<ExistAndActive, String> {

    private final UserRepository userRepository;
    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        return userRepository.existsByEmail(email);
    }
}
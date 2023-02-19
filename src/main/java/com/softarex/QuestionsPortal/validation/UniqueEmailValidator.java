package com.softarex.QuestionsPortal.validation;

import com.softarex.QuestionsPortal.repository.UserRepository;
import com.softarex.QuestionsPortal.validation.annotation.Unique;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UniqueEmailValidator implements
        ConstraintValidator<Unique, String> {

    private final UserRepository userRepository;
    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        return !userRepository.existsByEmail(email);
    } //вообще по этому поводу надо подумать потому что тут не особо понятно что оно тут выдает
    //плюс констрейнт уникальный
}

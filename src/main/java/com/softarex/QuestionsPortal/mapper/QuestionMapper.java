package com.softarex.QuestionsPortal.mapper;

import com.softarex.QuestionsPortal.dto.QuestionDto;
import com.softarex.QuestionsPortal.entity.Question;
import com.softarex.QuestionsPortal.service.UserService;
import org.mapstruct.BeanMapping;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(
        componentModel = "spring",
        uses = UserService.class, injectionStrategy = InjectionStrategy.FIELD,nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public abstract class QuestionMapper {

    @Autowired
    protected UserService userService;

    @Mapping(target = "recipientId", expression = "java(questionDto.getRecipientEmail() == null ? null : userService.getUserByEmail(questionDto.getRecipientEmail()).getId())")
    @Mapping(target = "senderId", expression = "java(userService.getAuthenticatedUser().getId())")
     public abstract Question dtoToQuestion(QuestionDto questionDto);

    @Mapping(target = "recipientEmail", expression = "java(userService.getUserById(question.getRecipientId()).getEmail())")
    public abstract QuestionDto questionToDto(Question question);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract Question updateQuestion(Question source, @MappingTarget Question target);

}

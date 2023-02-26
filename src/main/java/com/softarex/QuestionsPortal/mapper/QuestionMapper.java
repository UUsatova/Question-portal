package com.softarex.QuestionsPortal.mapper;

import com.softarex.QuestionsPortal.dto.AnswerDto;
import com.softarex.QuestionsPortal.dto.QuestionDto;
import com.softarex.QuestionsPortal.entity.Question;
import com.softarex.QuestionsPortal.service.AnswerService;
import com.softarex.QuestionsPortal.service.AppService;
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
        uses = {AppService.class}, injectionStrategy = InjectionStrategy.FIELD,nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, imports = AnswerDto.class)
public abstract class QuestionMapper {

    @Autowired
    protected AppService appService;

    @Autowired
    protected AnswerMapper answerMapper;


    @Mapping(target = "recipient", expression = "java(questionDto.getRecipientEmail() == null ? null : appService.getUserByEmail(questionDto.getRecipientEmail()))")
    @Mapping(target = "sender", expression = "java(appService.getAuthenticatedUser())")
    @Mapping(target = "answer", expression = "java(questionDto.getAnswerDto() == null ? null : answerMapper.dtoToAnswer(questionDto.getAnswerDto()))")
     public abstract Question dtoToQuestion(QuestionDto questionDto);

   @Mapping(target = "recipientEmail", expression = "java(question.getRecipient().isActive() ? question.getRecipient().getEmail() : \"Deleted user\")")
   @Mapping(target = "senderEmail", expression = "java(question.getSender().isActive() ? question.getSender().getEmail() : \"Deleted user\")")
   @Mapping(target = "answerDto",expression = "java(question.getAnswer() == null ? new AnswerDto() : answerMapper.answerToDto(question.getAnswer() ) )")
    public abstract QuestionDto questionToDto(Question question);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract Question updateQuestion(Question source, @MappingTarget Question target);

}

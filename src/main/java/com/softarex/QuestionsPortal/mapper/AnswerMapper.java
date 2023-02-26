package com.softarex.QuestionsPortal.mapper;

import com.softarex.QuestionsPortal.dto.AnswerDto;
import com.softarex.QuestionsPortal.entity.Answer;
import org.mapstruct.Mapper;

@Mapper
public interface AnswerMapper {


    AnswerDto answerToDto(Answer answer);

    Answer dtoToAnswer(AnswerDto answerDto);
}

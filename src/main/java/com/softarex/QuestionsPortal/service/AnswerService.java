package com.softarex.QuestionsPortal.service;

import com.softarex.QuestionsPortal.dto.AnswerDto;
import com.softarex.QuestionsPortal.entity.Answer;
import com.softarex.QuestionsPortal.mapper.AnswerMapper;
import com.softarex.QuestionsPortal.repository.AnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AnswerService {

    private final AnswerRepository answerRepository;

    private final AnswerMapper answerMapper;

    public Answer getActiveAnswerById(UUID id){
        return answerRepository.findActiveQuestionById(id);
    }

    public Answer addAnswer(AnswerDto answerDto){
        Answer answer = answerMapper.dtoToAnswer(answerDto);
        answer.setLocalDateTime(LocalDateTime.now());
       return answerRepository.save(answer);
    }
}

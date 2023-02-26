package com.softarex.QuestionsPortal.service;


import com.softarex.QuestionsPortal.dto.QuestionDto;
import com.softarex.QuestionsPortal.entity.Question;
import com.softarex.QuestionsPortal.entity.User;
import com.softarex.QuestionsPortal.exception.ItemNotFoundException;
import com.softarex.QuestionsPortal.mapper.QuestionMapper;
import com.softarex.QuestionsPortal.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;
    private final AppService appService;
    private static final Sort SORT =  Sort.by(Sort.Direction.DESC,"localDateTime");

    public Question getQuestionById(UUID id){
        return questionRepository.findById(id).orElseThrow(ItemNotFoundException::new);
    }

    public List<Question> getAllQuestionsUserAsked(UUID userID){ //проверка удален он или нет
        return questionRepository.findActiveQuestionsBySenderId(userID,  SORT);
    }

    public List<QuestionDto> getListWithDtoOffAllQuestionsUserAsked(){
        List<QuestionDto> questionDtoList = new ArrayList<>();
        for(Question question : getAllQuestionsUserAsked(appService.getAuthenticatedUser().getId())){
            questionDtoList.add(questionMapper.questionToDto(question));
        }
        return questionDtoList;

    }

    public List<Question> getAllQuestionsUserReceived(UUID userId){
        return questionRepository.findActiveQuestionsByRecipientId(userId,  SORT);
    }

    public List<QuestionDto> getListWithDtoOffAllQuestionsUserReceived(){
        List<QuestionDto> questionDtoList = new ArrayList<>();
        for(Question question : getAllQuestionsUserReceived(appService.getAuthenticatedUser().getId())){
            questionDtoList.add(questionMapper.questionToDto(question));
        }
        return questionDtoList;

    }

    public Question addQuestion(QuestionDto questionDto){
        Question question = questionMapper.dtoToQuestion(questionDto);
        question.setLocalDateTime(LocalDateTime.now());
        return questionRepository.save(question);
    }

    public void softDeleteQuestion(UUID id){
        Question question = getQuestionById(id);
        question.setActive(false);
        questionRepository.save(question);
    }

    public void  softDeleteAllUserQuestions(UUID userId){
        for (Question question: getAllQuestionsUserAsked(userId) ){
            question.setActive(false);
            questionRepository.save(question);
        }
    }

    public Question updateQuestion(QuestionDto questionDtoWithChanges){
        Question questionChanges = questionMapper.dtoToQuestion(questionDtoWithChanges);
        Question questionBeforeChanges = getQuestionById(questionChanges.getId());
        Question questionAfterChanges = questionMapper.updateQuestion(questionChanges,questionBeforeChanges);
        questionAfterChanges.setLocalDateTime(LocalDateTime.now());
        return questionRepository.save(questionAfterChanges);
    }

    public Question updateQuestion(Question questionWithChanges){
        Question questionBeforeChanges = getQuestionById(questionWithChanges.getId());
        Question questionAfterChanges = questionMapper.updateQuestion(questionWithChanges,questionBeforeChanges);
        return questionRepository.save(questionAfterChanges);
    }


}

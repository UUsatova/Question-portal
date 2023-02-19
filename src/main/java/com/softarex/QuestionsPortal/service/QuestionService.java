package com.softarex.QuestionsPortal.service;


import com.softarex.QuestionsPortal.dto.QuestionDto;
import com.softarex.QuestionsPortal.entity.Question;
import com.softarex.QuestionsPortal.mapper.QuestionMapper;
import com.softarex.QuestionsPortal.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;
    private final UserService userService;//Возможно лучше AppService

    public Question getQuestionById(UUID id){
        return questionRepository.findById(id).orElseThrow(); //выбросить свое исключение
    }

    public List<Question> getAllQuestionsUserAsked(){ //сделать проверку на то удален этот вопрос или нет
        UUID idOfAuthUser = userService.getAuthenticatedUser().getId();
        return questionRepository.findQuestionsBySenderId(idOfAuthUser);
    }

    public List<QuestionDto> getListWithDtoOffAllQuestionsUserAsked(){
        List<QuestionDto> questionDtoList = new ArrayList<>();
        for(Question question : getAllQuestionsUserAsked()){
            questionDtoList.add(questionMapper.questionToDto(question));
        }
        return questionDtoList;
    }

    public List<Question> getAllQuestionsUserAnswered(){
        UUID idOfAuthUser = userService.getAuthenticatedUser().getId();
        return questionRepository.findQuestionsBySenderId(idOfAuthUser);
    }

    public Question addQuestion(QuestionDto questionDto){
        Question question = questionMapper.dtoToQuestion(questionDto);
        return questionRepository.save(question);
    }

    public void deleteQuestion(UUID id){
        questionRepository.deleteById(id); //логика пометок
    }

    public Question updateQuestion(QuestionDto questionDtoWithChanges){
        Question questionChanges = questionMapper.dtoToQuestion(questionDtoWithChanges);
        Question questionBeforeChanges = getQuestionById(questionChanges.getId());
        Question questionAfterChanges = questionMapper.updateQuestion(questionChanges,questionBeforeChanges);
        return questionRepository.save(questionAfterChanges);
    }


}

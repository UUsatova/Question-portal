package com.softarex.QuestionsPortal.controller;

import com.softarex.QuestionsPortal.dto.AnswerDto;
import com.softarex.QuestionsPortal.entity.Question;
import com.softarex.QuestionsPortal.service.AnswerService;
import com.softarex.QuestionsPortal.service.QuestionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/answers")
public class AnswerController {
    private final QuestionService questionService;
    private final AnswerService answerService;

    @GetMapping
    public String viewHomePage(Model model){
        model.addAttribute("allQuestionsUserReceived", questionService.getListWithDtoOffAllQuestionsUserReceived());
        model.addAttribute("answer",new AnswerDto());
        return "answer-page";
    }

    @PostMapping
    public String addAnswer(@ModelAttribute("answer") @Valid AnswerDto answerDto){
        Question question = questionService.getQuestionById(answerDto.getQuestionId());
        question.setAnswer( answerService.addAnswer(answerDto));
        questionService.updateQuestion(question);
        return "redirect:/answers";
    }


}

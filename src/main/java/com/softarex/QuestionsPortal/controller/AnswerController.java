package com.softarex.QuestionsPortal.controller;

import com.softarex.QuestionsPortal.dto.AnswerDto;
import com.softarex.QuestionsPortal.entity.Question;
import com.softarex.QuestionsPortal.service.AnswerService;
import com.softarex.QuestionsPortal.service.QuestionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
@RequestMapping("/answers")
public class AnswerController {
    private final QuestionService questionService;
    private final AnswerService answerService;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;


    @GetMapping
    public String viewHomePage(Model model){
        model.addAttribute("allQuestionsUserReceived", questionService.getListWithDtoOffAllQuestionsUserReceived());
        model.addAttribute("answer",new AnswerDto());
        return "answer-page";
    }

    @PostMapping
    @MessageMapping("/add")
    @SendTo("/topic/answers")
    public String addAnswer(@ModelAttribute("answer") @Valid AnswerDto answerDto){
        Question question = questionService.getQuestionById(answerDto.getQuestionId());
        question.setAnswer( answerService.addAnswer(answerDto));
        questionService.updateQuestion(question);
        simpMessagingTemplate.convertAndSend("/topic/answers", question);
        return "redirect:questions";

    }


}

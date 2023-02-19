package com.softarex.QuestionsPortal.controller;


import com.softarex.QuestionsPortal.dto.QuestionDto;
import com.softarex.QuestionsPortal.group.Creation;
import com.softarex.QuestionsPortal.mapper.QuestionMapper;
import com.softarex.QuestionsPortal.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/questions")
public class QuestionController {

    private final QuestionService questionService;
    private final QuestionMapper questionMapper;

    @GetMapping//задать обратный порядок вывода(сверху самые поздние)
    public String viewHomePage(Model model){
        model.addAttribute("allQuestionsUserAsked", questionService.getListWithDtoOffAllQuestionsUserAsked());
        model.addAttribute("question", new QuestionDto());

        return "questions-page";
    }

    @PostMapping
    public String createNewQuestion(Model model, @ModelAttribute("question") @Validated(Creation.class) QuestionDto questionDto, BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("allQuestionsUserAsked", questionService.getListWithDtoOffAllQuestionsUserAsked());
            return "questions-page";
        }
        questionService.addQuestion(questionDto);
        return "redirect:/questions";
    }

    @GetMapping("/delete/{id}")
    public String deleteQuestion(@PathVariable UUID id) {
        questionService.deleteQuestion(id);
        return "redirect:/questions";
    }

    @GetMapping("/{id}")
    @ResponseBody
    public QuestionDto getQuestionById(@PathVariable UUID id) {
        return questionMapper.questionToDto(questionService.getQuestionById(id));
    }

    @PostMapping("/update")
    public String updateQuestion( @ModelAttribute("question") QuestionDto questionDto){
        questionService.updateQuestion(questionDto);
        return "redirect:/questions";
    }


}

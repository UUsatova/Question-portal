package com.softarex.QuestionsPortal.dto;

import com.softarex.QuestionsPortal.entity.AnswerType;
import com.softarex.QuestionsPortal.group.Creation;
import com.softarex.QuestionsPortal.group.Update;
import com.softarex.QuestionsPortal.validation.annotation.ExistAndActive;
import com.softarex.QuestionsPortal.validation.annotation.Unique;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDto {

    private UUID id;
    @NotBlank(groups = {Creation.class, Update.class},message = "Must not be blank")
    private String content;
    private AnswerDto answerDto;
    @ExistAndActive(groups = {Creation.class, Update.class},message = "Wrong user email")
    private String recipientEmail;
    private String senderEmail;
    private AnswerType answerType;
    private String options="";
    private LocalDateTime localDateTime;

    public String getLocalDateTimeString(){
        return localDateTime.format(DateTimeFormatter.ofPattern("d MMM uuuu HH:mm"));
    }
}
package com.softarex.QuestionsPortal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnswerDto {
    private UUID id;
    private String content;
    private UUID questionId;
    private LocalDateTime localDateTime;
    public String getLocalDateTimeString(){
        return localDateTime == null ? "" : localDateTime.format(DateTimeFormatter.ofPattern("d MMM uuuu HH:mm"));
    }

    //надо ли айди вопроса
}

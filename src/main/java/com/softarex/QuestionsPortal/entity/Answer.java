package com.softarex.QuestionsPortal.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "answers")
public class Answer {
    @Id
    @Column(name = "id")
    @UuidGenerator
    private UUID id;
    @Column(name = "content")
    private String content;
    @Column(name = "answer_type")
    private AnswerType answerType;
    @Column(name = "question_id")
    private UUID question_id;
    @Column(name = "isactive")//переименовать
    private boolean isActive = true;
    @Column(name = "time")
    private LocalDateTime localDateTime;
}

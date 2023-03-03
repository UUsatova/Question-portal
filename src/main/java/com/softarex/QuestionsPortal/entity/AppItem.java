package com.softarex.QuestionsPortal.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;


@Getter
@Setter
@MappedSuperclass
public class AppItem {

    @Id
    @Column(name = "id")
    @UuidGenerator
    private UUID id;
    @Column(name = "content")
    private String content;
    @Column(name = "answer_type")
    private AnswerType answerType;
    @Column(name = "isactive")//переименоваать
    private boolean isActive=true;
    @Column(name = "time")
    private LocalDateTime localDateTime;
}
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
@Table(name = "questions")
public class Question {
    @Id
    @Column(name = "id")
    @UuidGenerator
    private UUID id;
    @Column(name = "content")
    private String content;
    @Column(name = "answer_id")
    private UUID answerId;
    @Column(name = "recipient_id")
    private UUID recipientId;
    @Column(name = "sender_id")
    private UUID senderId;
    @Column(name = "answer_type")
    private AnswerType answerType;
    @Column(name = "options")
    private String options;
    @Column(name = "isactive")//переименовать
    private boolean isActive=true;
    @Column(name = "time")
    private LocalDateTime localDateTime;

}

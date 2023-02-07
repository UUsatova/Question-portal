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
    @Column(name = "question_recipient_id")
    private UUID questionRecipientId;
    @Column(name = "question_sender_id")
    private UUID questionSenderId;
    @Column(name = "answer_type")
    private AnswerType answerType;

}

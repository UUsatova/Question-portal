package com.softarex.QuestionsPortal.repository;

import com.softarex.QuestionsPortal.entity.Question;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface QuestionRepository extends JpaRepository<Question, UUID> {

    @Query("SELECT q FROM Question q WHERE q.isActive = true AND q.senderId=:id")
     List<Question> findActiveQuestionsBySenderId(UUID id, Sort sort);

    @Query("SELECT q FROM Question q WHERE q.isActive = true AND q.recipientId=:id")
    List<Question> findActiveQuestionsByRecipientId(UUID id);
}

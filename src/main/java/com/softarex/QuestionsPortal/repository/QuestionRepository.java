package com.softarex.QuestionsPortal.repository;

import com.softarex.QuestionsPortal.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface QuestionRepository extends JpaRepository<Question, UUID> {
    public List<Question> findQuestionsBySenderId(UUID id);
}

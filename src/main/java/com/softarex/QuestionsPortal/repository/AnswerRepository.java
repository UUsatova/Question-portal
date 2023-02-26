package com.softarex.QuestionsPortal.repository;

import com.softarex.QuestionsPortal.entity.Answer;
import com.softarex.QuestionsPortal.entity.Question;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface AnswerRepository extends JpaRepository<Answer, UUID> {
    @Query("SELECT a FROM Answer a WHERE a.isActive = true AND a.id=:id")
    Answer findActiveQuestionById(UUID id);
}

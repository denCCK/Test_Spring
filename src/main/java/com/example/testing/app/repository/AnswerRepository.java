package com.example.testing.app.repository;

import com.example.testing.app.model.Answer;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {
    List<Answer> findByQuestionId(Integer questionId);
    @Transactional
    void deleteByQuestionId(Integer questionId);

}
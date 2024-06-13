package com.example.testing.app.service;

import com.example.testing.app.model.Answer;
import com.example.testing.app.model.Question;
import com.example.testing.app.repository.AnswerRepository;
import com.example.testing.app.repository.QuestionRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository, AnswerRepository answerRepository) {
        this.questionRepository = questionRepository;
    }

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    public ResponseEntity<Question> getQuestionById(Integer id) {
        Optional<Question> question = questionRepository.findById(id);
        return question.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    public Question createQuestion(Question question) {
        return questionRepository.save(question);
    }

    public ResponseEntity<Question> updateQuestion(Integer id, Question question) {
        Optional<Question> existingQuestion = questionRepository.findById(id);
        if (existingQuestion.isPresent()) {
            question.setId(id);
            return ResponseEntity.ok(questionRepository.save(question));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<Void> deleteQuestion(Integer id) {
        Optional<Question> question = questionRepository.findById(id);
        if (question.isPresent()) {
            questionRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Transactional
    public void deleteQuestionWithAnswers(Integer questionId) {
        Optional<Question> question = questionRepository.findById(questionId);
        if (question.isPresent()) {
            questionRepository.delete(question.get());
        } else {
            throw new EntityNotFoundException("Question not found with id " + questionId);
        }
    }
}


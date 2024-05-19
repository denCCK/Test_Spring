package com.example.testing.app.service;

import com.example.testing.app.model.Answer;
import com.example.testing.app.repository.AnswerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AnswerService {

    private final AnswerRepository answerRepository;

    public AnswerService(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    public List<Answer> getAllAnswers() {
        return answerRepository.findAll();
    }

    public ResponseEntity<Answer> getAnswerById(Integer id) {
        Optional<Answer> answer = answerRepository.findById(id);
        return answer.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    public Answer createAnswer(Answer answer) {
        return answerRepository.save(answer);
    }

    public ResponseEntity<Answer> updateAnswer(Integer id, Answer answer) {
        Optional<Answer> existingAnswer = answerRepository.findById(id);
        if (existingAnswer.isPresent()) {
            answer.setId(id);
            return ResponseEntity.ok(answerRepository.save(answer));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<Void> deleteAnswer(Integer id) {
        Optional<Answer> answer = answerRepository.findById(id);
        if (answer.isPresent()) {
            answerRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public List<Answer> getAnswersByQuestionId(Integer questionId) {
        return answerRepository.findByQuestionId(questionId);
    }

    @Transactional
    public void deleteAnswersByQuestionId(Integer questionId) {
        answerRepository.deleteByQuestionId(questionId);
    }
}


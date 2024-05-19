package com.example.testing.app.controller;
import com.example.testing.app.model.Answer;
import com.example.testing.app.model.Question;
import com.example.testing.app.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping
    public ResponseEntity<Question> createQuestion(@RequestBody Question question) {
        return ResponseEntity.ok(questionService.createQuestion(question));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Question> getQuestion(@PathVariable Integer id) {
        return questionService.getQuestionById(id);
    }

    @GetMapping
    public ResponseEntity<List<Question>> getAllQuestions() {
        return ResponseEntity.ok(questionService.getAllQuestions());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Question> updateQuestion(@PathVariable Integer id, @RequestBody Question question) {
        return questionService.updateQuestion(id, question);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Integer id) {
        return questionService.deleteQuestion(id);
    }

    @DeleteMapping("/answers/{id}")
    public ResponseEntity<Void> deleteQuestionWithAnswers(@PathVariable Integer id) {
        questionService.deleteQuestionWithAnswers(id);
        return ResponseEntity.noContent().build();
    }
}


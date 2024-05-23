package com.example.testing.app.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "testsession_answer_result")
public class TestsessionAnswerResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "testsession_answer_result_id", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "testsession_result_id")
    private TestsessionResult testsessionResult;

    @ManyToOne(optional = false)
    @JoinColumn(name = "answer_id", nullable = false)
    private Answer answer;

    @Column(name = "answer_text", nullable = false)
    private String answerText;

}
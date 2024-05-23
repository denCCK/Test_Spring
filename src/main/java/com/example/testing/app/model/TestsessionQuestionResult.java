package com.example.testing.app.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "testsession_question_result")
public class TestsessionQuestionResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "testsession_question_result_id", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "testsession_result_id")
    private TestsessionResult testsessionResult;

    @ManyToOne(optional = false)
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    @Column(name = "is_correct", nullable = false)
    private Boolean isCorrect = false;

    @Column(name = "point", nullable = false)
    private Float point;

}
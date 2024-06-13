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

    @ManyToOne(optional = false)
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    @Column(name = "answer_img")
    private byte[] answerImg;

    @Column(name = "is_correct")
    private Boolean isCorrect;

    @Column(name = "compliance_text")
    private String complianceText;

    @Column(name = "compliance_img")
    private byte[] complianceImg;

    @Column(name = "answer_formula", length = Integer.MAX_VALUE)
    private String answerFormula;

    @Column(name = "is_formula")
    private Boolean isFormula;

    @Column(name = "is_compliance_formula")
    private Boolean isComplianceFormula;

}
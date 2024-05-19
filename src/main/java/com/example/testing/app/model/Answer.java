package com.example.testing.app.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "answer")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answer_id", nullable = false)
    private Integer id;

    @Column(name = "answer_text", nullable = false, length = 50)
    private String answerText;

    @Column(name = "answer_img")
    private byte[] answerImg;

    @Column(name = "is_correct")
    private Boolean isCorrect;

    @Column(name = "compliance_text", length = 50)
    private String complianceText;

    @Column(name = "compliance_img")
    private byte[] complianceImg;


    @ManyToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL,optional = false)
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

}

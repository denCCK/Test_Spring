package com.example.testing.app.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "question_test")
public class QuestionTest {
    @EmbeddedId
    private QuestionTestId id;

    @MapsId
    @ManyToOne(optional = false)
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    @MapsId
    @ManyToOne(optional = false)
    @JoinColumn(name = "test_id", nullable = false)
    private Test test;

}

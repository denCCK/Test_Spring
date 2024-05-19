package com.example.testing.app.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class QuestionTestId implements Serializable {
    private static final long serialVersionUID = -3989993839789178563L;
    @Column(name = "question_id", nullable = false)
    private Integer questionId;

    @Column(name = "test_id", nullable = false)
    private Integer testId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        QuestionTestId entity = (QuestionTestId) o;
        return Objects.equals(this.questionId, entity.questionId) &&
                Objects.equals(this.testId, entity.testId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(questionId, testId);
    }

}